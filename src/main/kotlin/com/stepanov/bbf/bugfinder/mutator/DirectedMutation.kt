package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.system.exitProcess

data class DirectedMutation(
    val transformation: Transformation,
    var scores: MutableList<Double>,
    var probability: Double
)

fun MutableList<DirectedMutation>.getRandomTransformation(): DirectedMutation? {
    if (this.isEmpty()) return null
    val generatedProbability = Random.nextDouble(100.0)
    var curProb = 0.0
    for (m in this) {
        if (curProb + m.probability > generatedProbability) {
            return m
        } else {
            curProb += m.probability
        }
    }
    return this.last()
}

fun MutableList<DirectedMutation>.updateProbabilities(
    transformation: Transformation,
    newProbability: Double
): Boolean {
    if (this.size < 2) return false
    val tr = this.find { it.transformation == transformation } ?: return false
    val oldProbability = tr.probability
    tr.probability = newProbability
    val diff = newProbability - oldProbability
    val k = diff / (this.size - 1)
    this.filter { it.transformation != transformation }.forEach { it.probability = it.probability - k }
    return true
}