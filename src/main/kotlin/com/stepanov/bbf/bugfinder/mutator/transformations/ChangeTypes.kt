package com.stepanov.bbf.bugfinder.mutator.transformations


import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator.generateRandomType
import com.stepanov.bbf.bugfinder.util.getType
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import kotlin.random.Random
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class ChangeTypes : Transformation() {

    override fun transform() {
        changeRandomTypeOnRandomType()
        if (context == null) return
        val properties = file.getAllPSIChildrenOfType<KtProperty>().filter { Random.nextBoolean() }
        for (prop in properties) {
            val type = if (prop.typeReference != null) prop.typeReference?.text else prop.getType(context)?.toString()
            val newType = changeType(type.toString())
            val typeRef = psiFactory.createTypeIfPossible(newType)
            typeRef?.let { newTypeRef ->
                if (prop.typeReference == null) {
                    prop.typeReference = newTypeRef
                    if (!checker.checkCompiling()) {
                        prop.typeReference = null
                        return@let
                    }
                } else {
                    checker.replaceNodeIfPossible(prop.typeReference!!, newTypeRef)
                }
            }
        }
    }

    private fun changeRandomTypeOnRandomType() {
        val types = file.getAllPSIChildrenOfType<KtTypeReference>()
        for (i in 0 until magicConst) {
            val type = types.randomOrNull() ?: return
            val newType =
                when (Random.nextBoolean()) {
                    true -> types.random()
                    else -> psiFactory.createTypeIfPossible(generateRandomType()) ?: continue
                }
            checker.replaceNodeIfPossible(type.node, newType.node)
        }
    }

    private fun changeType(type: String): String {
        val res = StringBuilder()
        val curType = StringBuilder()
        if (type.split(">", "<", ",").size == 1) return generateMostCommonType(type)
        for (ch in type) {
            if (ch == '<' || ch == ',' || ch == '>') {
                if (curType.toString().contains(Regex("[A-Za-z]"))) {
                    res.append("${generateMostCommonType("${curType.filter { it != ',' }.trim()}")}$ch") //"$res"
                    curType.clear()
                } else res.append(ch)
            } else curType.append(ch)
        }
        res.append(curType)
        return res.toString()
    }

    private fun generateMostCommonType(type: String): String {
        //Add random
        var res: String
        if (replacements.containsKey(type)) {
            if (Random.nextBoolean()) return type
            res = replacements.getValue(type).random()
            while (replacements.containsKey(res)) {
                if (Random.nextBoolean()) return res
                res = replacements.getValue(res).random()
            }
        } else return type
        return res
    }

    //Iterators?
    //Seq
    private val replacements = mapOf(
        "Byte" to listOf("Number"),
        "Short" to listOf("Number"),
        "Int" to listOf("Number"),
        "Long" to listOf("Number"),
        "Float" to listOf("Number"),
        "Double" to listOf("Number"),
        "String" to listOf("CharSequence"),
        "Collection" to listOf("Iterable"),
        "MutableCollection" to listOf("Collection", "MutableIterable"),
        "MutableIterable" to listOf("Iterable"),
        "List" to listOf("Collection"),
        "Set" to listOf("Collection"),
        "List" to listOf("Collection"),
        "MutableList" to listOf("List", "MutableCollection"),
        "MutableSet" to listOf("Set", "MutableCollection"),
        "MutableMap" to listOf("Map")
    )

    private val context = PSICreator.analyze(checker.curFile.psiFile)//checker.curFile.ctx
    private val magicConst = 100
}