package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.util.*
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import kotlin.random.Random
import kotlin.streams.toList
import kotlin.system.exitProcess

class AddRandomNode : Transformation() {
    override fun transform() {
        val randConst = Random.nextInt(numOfTries.first, numOfTries.second)
        val filteredNodes = file.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
        val nodes = file.node.getAllChildrenNodes()
        val nodeDB = File("database.txt").bufferedReader().lines().toList()
        log.debug("Trying to add some nodes $randConst times")
        //From same file
        for (i in 0..randConst / 2) {
            println("I = $i")
            val randomNode = filteredNodes.randomOrNull()?.psi?.copy() ?: break
            val placeToInsert = nodes.random().psi
            println("insert ${randomNode.text} after $placeToInsert ${placeToInsert.text}")
            placeToInsert.addAfterWithWhitespace(randomNode).let {
                if (!checker.checkCompiling()) {
                    it.first.delete()
                    it.second?.delete()
                    it.third?.delete()
                }
            }
            //println(file.text)
        }
        println(file.text)
        exitProcess(0)
        for (i in 0..randConst / 2) {
            val nodeToAdd = nodeDB.random().dropLast(1).takeLastWhile { it != '[' }.split(", ").random()
        }
        exitProcess(0)
    }

    private val numOfTries = if (checker.project.files.size == 1) 50 to 500 else 1000 to 2000
}