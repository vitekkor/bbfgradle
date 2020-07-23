package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.generator.constructor.util.StandardLibraryInheritanceTree
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import org.jetbrains.kotlin.types.KotlinType
import kotlin.random.Random


fun generateRandomType(upperBounds: KotlinType? = null): String {
    if (upperBounds == null) return if (Random.nextBoolean()) generateContainer() else generatePrimitive()
    val implementations = StandardLibraryInheritanceTree.getSubtypesOf(upperBounds.toString().substringBefore("<"))
    return primitives.intersect(implementations).randomOrNull()
        ?: containers.intersect(implementations).randomOrNull()
        ?: upperBounds.toString()
}


private fun generateContainer(): String {
    val container = containers.random()
    return when (Random.nextBoolean(10)) {
        true ->
            if (container.endsWith("Map")) "$container<${generateContainer()}, ${generateContainer()}>"
            else "$container<${generateContainer()}>"
        false ->
            if (container.endsWith("Map")) "$container<${generatePrimitive()}, ${generatePrimitive()}>"
            else "$container<${generatePrimitive()}>"
    }
}

private fun Random.nextBoolean(prob: Int) = Random.nextInt(0, 100) < prob

private fun generatePrimitive(): String = primitives.random()

val primitives = listOf(
    "Int", "Double", "String", "Float", "Long", "Short", "Byte", "Char",
    "UByte", "ULong", "UShort", "UInt"
)

val containers = listOf("ArrayList", "List", "Set", "Map", "Array", "HashMap", "MutableMap", "HashSet", "LinkedHashMap")