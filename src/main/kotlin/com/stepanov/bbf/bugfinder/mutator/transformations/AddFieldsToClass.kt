package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomPropertyGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import kotlin.system.exitProcess

class AddFieldsToClass: Transformation() {

    override fun transform() {
        val randomKlass = file.getAllPSIChildrenOfType<KtClassOrObject>()
        val ctx = PSICreator.analyze(file) ?: return
        val rk = randomKlass.first()
        val gClass = GClass.fromPsi(rk)
        val rpg = RandomPropertyGenerator(file as KtFile, ctx, gClass)
        val newInterestingProperty = rpg.generateInterestingProperty()
        //Add override modifier
        println(newInterestingProperty?.first?.text)
        exitProcess(0)
    }
}