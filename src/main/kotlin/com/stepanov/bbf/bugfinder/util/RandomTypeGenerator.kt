package com.stepanov.bbf.bugfinder.util

import kotlin.random.Random


fun generateRandomType(): String =
    if (Random.nextBoolean()) generateContainer() else generatePrimitive()

private fun generateContainer(): String {
    val container = listOf("ArrayList", "List", "Set", "Map", "Array", "HashMap").random()
    return when (Random.nextBoolean()) {
        true -> if (container.endsWith("Map")) "$container<${generateContainer()}, ${generateContainer()}>" else "$container<${generateContainer()}>"
        false -> if (container.endsWith("Map")) "$container<${generatePrimitive()}, ${generatePrimitive()}>" else "$container<${generatePrimitive()}>"
    }
}

private fun generatePrimitive(): String =
    listOf("Int", "Double", "String", "Float", "Long", "Short", "Byte", "Char",
    "UByte", "ULong", "UShort", "UInt").random()

