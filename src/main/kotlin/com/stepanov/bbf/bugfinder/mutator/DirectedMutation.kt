package com.stepanov.bbf.bugfinder.mutator

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.ifExists
import java.io.File
import kotlin.random.Random

data class DirectedMutation(
    val fileName: String,
    val transformation: Transformation,
    var scores: MutableList<Double>,
    var probability: Double
) {
    companion object {
        val file = File("tmp/mutationStatistics.txt")

        fun saveMutationsInformationInFile(directedMutations: List<DirectedMutation>) {
            val pathToSeed = directedMutations.first().fileName
            val resString = StringBuilder()
            //TODO if exist
            //file.applyIf ({ it.exists }) { it.readText() }
            file.ifExists {
                readLines()
                    .filter { it.substringAfter('{').substringBefore(';') != pathToSeed }
                    .forEach { resString.appendLine(it) }
            }
            for ((filename, mutation, probability, scores) in directedMutations) {
                val mutationName = mutation.javaClass.name
                resString.appendLine("{$filename;$mutationName;$probability;$scores}")
            }
            file.writeText(resString.toString())
        }

        fun deserializeDirectedMutations(originalDirectedMutations: List<DirectedMutation>): List<DirectedMutation> {
            val pathToSeed = originalDirectedMutations.first().fileName
            if (!file.exists()) return originalDirectedMutations
            val directedMutationsInfo =
                file.readText()
                    .split("\n")
                    .filter { it.trim().isNotEmpty() }
                    .mapNotNull {
                        val (fi, n, s, p) = it.drop(1).dropLast(1).split(";")
                        if (fi == pathToSeed) {
                            val scores = s
                                .substringAfter('[')
                                .substringBefore(']')
                                .split(',')
                                .mapNotNull { it.trim().toDoubleOrNull() }
                            val probability = p.toDouble()
                            val mutationAsClass =
                                originalDirectedMutations.find { it.transformation.javaClass.name == n }!!
                            DirectedMutation(fi, mutationAsClass.transformation, scores.toMutableList(), probability)
                        } else null
                    }
            val res = originalDirectedMutations.map { originalMutation ->
                directedMutationsInfo.find { it.transformation ==  originalMutation.transformation } ?: originalMutation
            }
            println()
            return res
        }
    }
}

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