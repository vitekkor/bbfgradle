package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.util.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import kotlin.random.Random
import kotlin.system.exitProcess
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory as psiFactory

class AddRandomDS : Transformation() {

    private val ctx = PSICreator.analyze(file)!!
    private val randomFunGenerator = RandomFunctionGenerator(file as KtFile, ctx)
    private val randomClassGenerator = RandomClassGenerator(file as KtFile, ctx)

    override fun transform() {
        repeat(1) {
            randomClassGenerator.generateAndAddToFile()
            //randomFunGenerator.generate()?.let { file.addToTheEnd(it) }
        }
        checker.checkCompiling()
    }

}