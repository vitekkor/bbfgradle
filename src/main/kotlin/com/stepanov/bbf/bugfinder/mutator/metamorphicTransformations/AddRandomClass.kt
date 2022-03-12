package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomClassGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.addToTheTop
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import kotlin.random.Random

class AddRandomClass : MetamorphicTransformation() {
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        val ktFile = Transformation.file as KtFile
        if (ctx == null) return
        val parentClass = ktFile.getAllPSIChildrenOfType<KtClassOrObject>().randomOrNull()
        val klassGenerator =
            if (parentClass != null && Random.getTrue(50)) {
                val parentClassAsGClass = GClass.fromPsi(parentClass)
                RandomClassGenerator(ktFile, ctx!!, 0, parentClassAsGClass)
            } else {
                RandomClassGenerator(ktFile, ctx!!)
            }
        val newClass = klassGenerator.generate() ?: return
        val addedClass = ktFile.addToTheTop(newClass)
    }
}