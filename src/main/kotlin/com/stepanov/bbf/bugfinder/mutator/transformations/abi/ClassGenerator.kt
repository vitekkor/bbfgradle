package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.bugfinder.util.addImport
import com.stepanov.bbf.bugfinder.util.addToTheEnd
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random
import kotlin.system.exitProcess

abstract class ClassGenerator(
    open val file: KtFile,
    open val ctx: BindingContext,
    open val depth: Int = 0
) : DSGenerator(file, ctx) {

    var gClass: GClass = GClass()
    abstract val classWord: String

    abstract fun generateModifiers(): List<String>
    abstract fun generateConstructor(): List<String>
    abstract fun generateSupertypes(): List<String>
    abstract fun generateAnnotations(): List<String>

    fun calcImports(): List<String> = UsageSamplesGeneratorWithStLibrary.calcImports(file)

    open fun generate(): PsiElement? {
        gClass.classWord = classWord
        gClass.annotations = generateAnnotations()
        gClass.modifiers = generateModifiers()
        gClass.name = Random.getRandomVariableName(3).capitalize()
        gClass.typeParams = generateTypeParams(true)
        gClass.constructorArgs = generateConstructor()
        gClass.supertypes = generateSupertypes()
        gClass.body = ClassBodyGenerator(file, ctx, gClass, depth + 1).generateBodyAsString()
        return gClass.toPsi()
    }

    fun generateAndAddToFile(): PsiElement? {
        generate()?.let {
            val added = file.addToTheEnd(it)
            calcImports().forEach { file.addImport(it.substringBeforeLast('.'), true) }
            return added
        } ?: return null
    }

}