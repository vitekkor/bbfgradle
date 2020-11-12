package com.stepanov.bbf.bugfinder.mutator.transformations.abi

import com.intellij.psi.PsiElement
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

    val gClass: GClass = GClass()
    abstract val classWord: String

    abstract fun generateModifiers(): List<String>
    abstract fun generateConstructor(): List<String>
    abstract fun generateSupertypes(): List<String>

    fun calcImports(): List<String> = UsageSamplesGeneratorWithStLibrary.calcImports(file)

    open fun generate(): PsiElement? {
        val gkl = GClass.fromPsi(file.getAllPSIChildrenOfType<KtClassOrObject>()[1])
        val newBody = ClassBodyGenerator(file, ctx, gkl, depth + 1).generateBodyAsString()
        println("newBody = $newBody")
        exitProcess(0)
        for (kl in file.getAllPSIChildrenOfType<KtClassOrObject>()) {
            val gClass1 = GClass.fromPsi(kl)
            println(gClass1)
        }
        exitProcess(0)
        gClass.classWord = classWord
        gClass.modifiers = generateModifiers()
        gClass.name = Random.getRandomVariableName(3).capitalize()
        gClass.typeParams = generateTypeParams()
        gClass.constructorArgs = generateConstructor()
        gClass.supertypes = generateSupertypes()
        gClass.body = ClassBodyGenerator(file, ctx, gClass, depth + 1).generateBodyAsString()
        println(gClass)
        exitProcess(0)
        return null
    }

    fun generateAndAddToFile(): Boolean {
        generate()?.let {
            file.addToTheEnd(it)
            calcImports().forEach { file.addImport(it.substringBeforeLast('.'), true) }
        } ?: return false
        return true
    }

}