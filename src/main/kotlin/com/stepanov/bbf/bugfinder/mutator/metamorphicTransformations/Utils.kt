package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.stepanov.bbf.bugfinder.mutator.MetamorphicMutator
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.bugfinder.util.name
import kotlin.math.abs
import kotlin.random.Random

fun generateOneVariableExpression(
    scope: HashMap<MetamorphicMutator.Variable, MutableList<String>>,
    variable: MetamorphicMutator.Variable,
    expected: Boolean
): String {
    val values = scope[variable]!!
    when (variable.type.name) {
        in listOf("Int", "Byte", "Short", "Long", "UInt", "ULong") -> {
            val max = values.maxOf { it.toLong() }
            val min = values.minOf { it.toLong() }
            val operator: String
            var value: String
            val suffix = if (variable.type.name!![0] == 'U') "u" else ""
            if (values.size > 1 && Random.getTrue(50)) {
                val k = if (Random.nextBoolean()) Random.nextInt() else 1
                operator = if (expected) "in" else "!in"
                value = "${k * min}$suffix..${k * max}$suffix"
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
                    "$variable in listOf${values.joinToString("\",\"", "(\"", "\")")}"
                else
                    "$variable.contains(\"${values.random()}${values.random()}\")"
            } else {
                if (values.isEmpty())
                    if (expected) "$variable.isEmpty()" else "$variable.isNotEmpty()"
                else {
                    if (expected) "$variable == \"${values.joinToString("")}\"" else "$variable.contains(\"${values.joinToString("")}${Random.nextInt()}\""
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
            return ""
        }
        else -> return ""
    }
}

fun Random.nextCharNotIn(list: List<Char>): Char {
    var next = this.nextInt(256).toChar()
    while (next in list) {
        next = this.nextInt(256).toChar()
    }
    return next
}