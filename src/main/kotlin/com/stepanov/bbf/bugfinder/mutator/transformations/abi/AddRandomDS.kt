package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtFile
import kotlin.system.exitProcess

class AddRandomDS : Transformation() {

    private val ctx = PSICreator.analyze(file)!!
    private val randomFunGenerator = RandomFunctionGenerator(file as KtFile, ctx)
    private val randomClassGenerator = RandomClassGenerator(file as KtFile, ctx)
    private val randomInterfaceGenerator = RandomInterfaceGenerator(file as KtFile, ctx)

    override fun transform() {
        repeat(1) {
            randomInterfaceGenerator.generateAndAddToFile()
            //randomFunGenerator.generate()?.let { file.addToTheEnd(it) }
        }
        val res = checker.checkCompiling()
        println(res)
        println(file.text)
        exitProcess(0)
        if (!res) {
            println(file.text)
            exitProcess(0)
        }
    }

}