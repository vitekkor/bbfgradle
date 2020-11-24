package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.*
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.util.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.psi.KtFile
import kotlin.system.exitProcess

class AddRandomDS : Transformation() {

    private var ctx = PSICreator.analyze(file)!!
    var tries = 15
    var enoughClasses = 10

    private fun update() {
        ctx = PSICreator.analyze(file)!!
        GeneratorFactory.update(file as KtFile, ctx)
    }

    override fun transform() {
        var successClasses = 0
        for (i in 0 until tries) {
            update()
            val generator = GeneratorFactory.getRandomGenerator()
            val addedPsiElement = generator.generateAndAddToFile() ?: continue
            val res = checker.checkCompiling()
            if (!res) {
                val psiWhiteSpace = Factory.psiFactory.createWhiteSpace("\n")
                addedPsiElement.replaceThis(psiWhiteSpace)
                if (!checker.checkCompiling()) {
                    println(file.text)
                    exitProcess(0)
                }
            } else if (generator is ClassGenerator) successClasses++
            if (successClasses == enoughClasses) break
        }
    }

}