package com.stepanov.bbf.reduktor.util

import ru.spbstu.kotlin.generate.util.*
import java.util.*

const val LINE_SIZE = 5
const val RANDOM_CONST = 5

fun generateDefValuesAsString(type: String): String {
    return when {
        type == "String" -> "\"${generateDefValuesForDefaultTypes<String>(type)}\""
        type == "Char" -> "\'${generateDefValuesForDefaultTypes<Char>(type)}\'"
        type == "Int" -> generateDefValuesForDefaultTypes<Int>(type).toString()
        type == "Double" -> generateDefValuesForDefaultTypes<Double>(type).toString()
        type == "Boolean" -> generateDefValuesForDefaultTypes<Boolean>(type).toString()
        type == "Byte" -> generateDefValuesForDefaultTypes<Byte>(type).toString()
        type == "Long" -> generateDefValuesForDefaultTypes<Long>(type).toString()
        type == "Short" -> generateDefValuesForDefaultTypes<Short>(type).toString()
        type == "Float" -> generateDefValuesForDefaultTypes<Float>(type).toString()
        type.startsWith("List") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "listOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "listOf()"
        }
        type.startsWith("ArrayList") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "arrayListOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "arrayListOf()"
        }
        type.startsWith("MutableList") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "mutableListOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "mutableListOf()"
        }
        type.startsWith("Set") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "setOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "setOf()"
        }
        type.startsWith("MutableSet") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "mutableSetOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "setOf()"
        }
        type.startsWith("Array") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "arrayOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "arrayOf()"
        }
        type.startsWith("MutableArray") -> {
            if (type.contains('<'))
                createDefaultValueForContainer(
                    "mutableArrayOf(",
                    type.substringAfter('<').substringBeforeLast('>')
                )
            else "arrayOf()"
        }
        type.startsWith("Pair") -> {
            createDefaultValueWithToOperator(type, 1)
        }
        type.startsWith("Map") -> {
            createDefaultValueWithToOperator(type, /*Random().nextInt(randomConst)*/5)
        }
        type.contains("->") -> {
            val returnType = type.takeLastWhile { it != '>' }.trim().dropLast(1)
            "{${generateDefValuesAsString(returnType)}}"
        }
        type.startsWith("Unit") -> ""
        else -> {
            "$type()"
        }
    }
}


@Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
private fun <T> generateDefValuesForDefaultTypes(type: String): T =
    when (type) {
        "Int" -> Random().nextInt()
        "Double" -> Random().nextDouble()
        "Boolean" -> Random().nextBoolean()
        "Char" -> Random().nextChar()
        "Byte" -> Random().nextByte()
        "Long" -> Random().nextLong()
        "Short" -> Random().nextShort()
        "Float" -> Random().nextFloat()
        else -> Random().nextString(
            ('a'..'z').asCharSequence(),
            LINE_SIZE, LINE_SIZE + 1
        )
    } as T


private fun getLeftAndRightTypes(type: String): Pair<String, String> {
    var left = ""
    var right = type
    for (t in type) {
        right = right.drop(1)
        if (t == ',') {
            val sumL = left.count { it == '<' } - left.count { it == '>' }
            val sumR = right.count { it == '>' } - right.count { it == '<' }
            if (sumL == sumR && sumL == 1) {
                return left.dropWhile { it != '<' }.drop(1).trim() to right.dropLast(1).trim()
                //println("TYPE LEFT = ${left.dropWhile { it != '<' }.drop(1).trim()} RIGHT = ${right.dropLast(1).trim()}")
            }
        }
        left += t
    }
    return "" to ""
}

private fun createDefaultValueWithToOperator(type: String, size: Int): String {
    val res = StringBuilder()
    val types = getLeftAndRightTypes(type)
    if (size == 1)
        res.append("(")
    else
        res.append("mapOf(")
    repeat(size) {
        res.append(generateDefValuesAsString(types.first))
        res.append(" to ")
        res.append(generateDefValuesAsString(types.second))
        res.append(", ")
    }
    res.replace(res.length - 2, res.length, ")")
    return res.toString()
}

private fun createDefaultValueForContainer(name: String, typeParam: String): String {
    //TODO !!!!!!!!! REPAIR IT FOR {}
    if (typeParam.contains('{')) return ""
    val values = StringBuilder()
    values.append(name)
    repeat(Random().nextInt(RANDOM_CONST) + 1) {
        values.append(
            "${generateDefValuesAsString(
                typeParam
            )}, "
        )
    }
    values.replace(values.length - 2, values.length, ")")
    return values.toString()
}
