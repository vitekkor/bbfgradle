package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtFile
import kotlin.random.Random
import kotlin.system.exitProcess

class AddRandomDS : Transformation() {

    private var ctx = PSICreator.analyze(file)!!
    private var randomFunGenerator = RandomFunctionGenerator(file as KtFile, ctx)
    private var randomClassGenerator = RandomClassGenerator(file as KtFile, ctx)
    private var randomInterfaceGenerator = RandomInterfaceGenerator(file as KtFile, ctx)
    var tries = 10
    var enough = 4

    fun update() {
        ctx = PSICreator.analyze(file)!!
        randomFunGenerator = RandomFunctionGenerator(file as KtFile, ctx)
        randomClassGenerator = RandomClassGenerator(file as KtFile, ctx)
        randomInterfaceGenerator = RandomInterfaceGenerator(file as KtFile, ctx)
    }

    override fun transform() {
        var success = 0
        for (i in 0 until tries) {
            update()
            val addedClass = (if (Random.nextBoolean()) {
                randomInterfaceGenerator.generateAndAddToFile()
            } else randomClassGenerator.generateAndAddToFile())
                ?: continue
            val res = checker.checkCompiling()
            if (!res) {
                val psiWhiteSpace = Factory.psiFactory.createWhiteSpace("\n")
                addedClass.replaceThis(psiWhiteSpace)
                if (!checker.checkCompiling()) {
                    println(file.text)
                    exitProcess(0)
                }
            } else success++
            if (success == enough) break
        }
    }

}