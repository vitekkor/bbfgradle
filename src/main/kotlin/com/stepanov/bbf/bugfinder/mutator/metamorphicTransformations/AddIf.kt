package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.name
import kotlin.math.abs
import kotlin.random.Random

class AddIf : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val exp = Random.nextBoolean()
        val predicate = synthesisPredicate(scope, exp, Random.nextInt(4))
        val mutations = removeMutation(AddIf::class)
        var tmp = tmpMutationPoint
        executeMutations(tmp, scope, exp, mutations)
        val thenStatement = tmp.children.joinToString("\n") { it.text }.split("\n").drop(1).joinToString("\n")
        if (thenStatement.isEmpty()) {
            return
        }
        tmp = tmpMutationPoint
        executeMutations(tmp, scope, !exp, mutations)
        val elseStatement =
            if (Random.getTrue(15)) "else {${
                tmp.children.joinToString("\n") { it.text }.split("\n").drop(1).joinToString("\n")
            }}" else ""
        addAfterMutationPoint(mutationPoint) { it.createExpression("if ($predicate) {$thenStatement}$elseStatement") }
    }

    fun synthesisPredicate(scope: HashMap<Variable, MutableList<String>>, expected: Boolean, depth: Int): Expression {
        if (depth == 0)
            return synthesisAtomic(scope, expected)
        return when (Random.nextInt(4)) {
            0 -> synthesisNegation(scope, expected, depth)
            1 -> synthesisConjunction(scope, expected, depth)
            2 -> synthesisDisjunction(scope, expected, depth)
            else -> synthesisAtomic(scope, expected)
        }
    }

    private fun synthesisAtomic(scope: HashMap<Variable, MutableList<String>>, expected: Boolean): Expression {
        val variable = scope.keys.randomOrNull() ?: return Expression(expected.toString())
        val generated = generateOneVariableExpression(scope, variable, expected)
        return Expression(generated)
/*        if (expected) {
            val variable = scope.keys.randomOrNull() ?: return Expression("true")
            return Expression(generateVariablesExpression(scope, variable))
        } else {
            val variable1 = scope.keys.randomOrNull() ?: return Expression("false")
            val variable2 = scope.keys.randomOrNull() ?: return Expression("false")
            if (variable1 == variable2) return Expression("$variable1 == $variable2")
            return Expression(generateVariablesExpression(scope, variable1, variable2))
        }*/
    }

    private fun synthesisNegation(
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean,
        depth: Int
    ): Expression {
        return Expression("!", synthesisPredicate(scope, !expected, depth - 1))
    }

    inner class Expression(val s: String, val left: Expression? = null, val right: Expression? = null) {
        override fun toString(): String {
            if (right == null && left == null)
                return s
            return if (right != null) "($left) $s ($right)" else "$s($left)"
        }
    }

    private fun synthesisConjunction(
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean,
        depth: Int
    ): Expression {
        val left: Boolean
        val right: Boolean
        if (expected) {
            left = true
            right = true
        } else if (Random.nextBoolean()) {
            left = true
            right = false
        } else {
            left = false
            right = Random.nextBoolean()
        }
        val leftPred = synthesisPredicate(scope, left, depth - 1)
        val rightPred = synthesisPredicate(scope, right, depth - 1)
        return Expression("&&", leftPred, rightPred)
    }

    private fun synthesisDisjunction(
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean,
        depth: Int
    ): Expression {
        val left: Boolean
        val right: Boolean
        if (!expected) {
            left = false
            right = false
        } else if (Random.nextBoolean()) {
            left = true
            right = false
        } else {
            left = true
            right = Random.nextBoolean()
        }
        val leftPred = synthesisPredicate(scope, left, depth - 1)
        val rightPred = synthesisPredicate(scope, right, depth - 1)
        return Expression("||", leftPred, rightPred)
    }

    private fun generateOneVariableExpression(
        scope: HashMap<Variable, MutableList<String>>,
        variable: Variable,
        expected: Boolean
    ): String {
        val values = scope[variable]!!
        when (variable.type.name) {
            in listOf("Int", "Byte", "Short", "Long", "UInt", "ULong") -> {
                val max = values.maxOf { it.replace("""\.to.*\(\)""".toRegex(), "").toLongOrNull() ?: Long.MAX_VALUE }
                val min = values.minOf { it.replace("""\.to.*\(\)""".toRegex(), "").toLongOrNull() ?: Long.MIN_VALUE }
                val operator: String
                var value: String
                val suffix = if (variable.type.name!![0] == 'U') "u" else ""
                if (values.size > 1 && Random.getTrue(50)) {
                    val k =
                        if (Random.nextBoolean())
                            if (suffix.isNotEmpty())
                                Random.nextInt(Int.MAX_VALUE / 1000)
                            else
                                Random.nextInt()
                        else 1
                    operator = if (expected) "in" else "!in"
                    value = "${k * min}$suffix..${k * max}"
                } else if (values.size > 1) {
                    val maxOrMin = Random.nextBoolean()
                    value = if (maxOrMin) max.toString() else min.toString()
                    operator = if (expected) {
                        if (maxOrMin) "<=" else ">="
                    } else {
                        if (maxOrMin) ">" else "<"
                    }
                } else {
                    val x = Random.nextInt(Int.MAX_VALUE)
                    value = min.toString()
                    operator = when (Random.nextInt(4)) {
                        0 -> if (expected) ">=" else ">"
                        1 -> if (expected) "<=" else ">"
                        2 -> if (expected) "==" else "!="
                        else -> {
                            value = x.toString()
                            if (!((x > min) xor expected)) "<" else ">"
                        }
                    }
                }
                return "$variable $operator $value$suffix"
            }
            "String" -> {
                return if (values.size > 1) {
                    if (expected)
                        "$variable in listOf${values.joinToString("\",\"", "(\"", "\")") { it.replace("\"", "") }}"
                    else
                        "$variable.contains(\"${values.random().replace("\"", "")}${values.random()}\")"
                } else {
                    if (values.isEmpty())
                        if (expected) "$variable.isEmpty()" else "$variable.isNotEmpty()"
                    else {
                        if (expected)
                            "$variable == \"${values.joinToString("") { it.replace("\"", "") }}\""
                        else
                            "$variable.contains(\"${values.joinToString("") { it.replace("\"", "") }}" +
                                    "${Random.nextInt()}\")"
                    }
                }
            }
            "Char" -> {
                return if (expected)
                    "$variable in ${values.joinToString("", "\"", "\"")}"
                else
                    "$variable == \'${Random.nextCharNotIn(values.map { it[0] })}\'"
            }
            "Boolean" -> {
                val valuesAreSimilar = values.toSet().size == 1
                return if (valuesAreSimilar) {
                    if (values[0].toBoolean() == expected) "$variable" else "!$variable"
                } else
                    if (expected)
                        "$variable || ${
                            if (scope.keys.size > 1) generateOneVariableExpression(scope, scope.keys.random(), true)
                            else "true"
                        }"
                    else "$variable && ${
                        if (scope.keys.size > 1) generateOneVariableExpression(scope, scope.keys.random(), false)
                        else "true"
                    }"
            }
            in listOf("Double", "Float") -> {
                val max = values.maxOf { it.toDouble() }
                val min = values.minOf { it.toDouble() }
                val operator: String
                var value: String
                val suffix = if (variable.type.name == "Float") "f" else ""
                return if (values.size > 1 && Random.getTrue(50)) {
                    operator = if (expected) "in" else "!in"
                    value = min.rangeTo(max).toString()
                    "$variable $operator $value"
                } else if (values.size > 1) {
                    val maxOrMin = Random.nextBoolean()
                    value = if (maxOrMin) max.toString() else min.toString()
                    operator = if (expected) {
                        if (maxOrMin) "<=" else ">="
                    } else {
                        if (maxOrMin) ">" else "<"
                    }
                    "$variable $operator $value$suffix"
                } else {
                    val x = Random.nextDouble(Double.MAX_VALUE)
                    value = min.toString()
                    operator = when (Random.nextInt(4)) {
                        0 -> if (expected) ">=" else ">"
                        1 -> if (expected) "<=" else ">"
                        2 -> if (expected) "==" else "!="
                        else -> {
                            value = x.toString()
                            if (!((abs(x - min) < 0.0001) xor expected)) ">" else "<"
                        }
                    }
                    if (min == x) "Math.abs($variable - $value$suffix) $operator 0.0001" else "$variable $operator $value$suffix"
                }

            }
            in listOf("List", "Array", "ArrayList") -> { //TODO COLLECTIONS
                variable.type.getNameWithoutError().replace("List<|>".toRegex(), "")
                return expected.toString()
            }
            else -> return expected.toString()
        }
    }

    private fun Random.nextCharNotIn(list: List<Char>): Char {
        var next = this.nextInt(256).toChar()
        while (next in list) {
            next = this.nextInt(256).toChar()
        }
        return next
    }

}