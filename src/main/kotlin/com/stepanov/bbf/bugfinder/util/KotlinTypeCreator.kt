package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.reduktor.parser.PSICreator
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.ImportPath
import org.jetbrains.kotlin.resolve.bindingContextUtil.getAbbreviatedTypeOrType
import org.jetbrains.kotlin.types.KotlinType
import kotlin.system.exitProcess

object KotlinTypeCreator {
    fun createType(file: KtFile, type: String): KotlinType? {
        if (type.contains("??") || type.contains("ERROR")) return null
        val fileCopy = file.copy() as KtFile
        val prefix = fileCopy.packageDirective?.text + "\n" + fileCopy.importList?.text + "\n"
        fileCopy.packageDirective?.delete()
        fileCopy.importList?.delete()
        val typeParams =
            calculateTypeArgs(type)
                .filter { ta -> file.getAllPSIChildrenOfType<KtClass>().all { it.name != ta } }
                .filter { ta -> UsageSamplesGeneratorWithStLibrary.findPackageForType(ta) == null }
                .let { if (it.isEmpty()) "" else it.joinToString(prefix = "<", postfix = ">") }
        val typeWithoutBounds =
            type.splitWithoutRemoving(Regex(""":\s*\w+""")).filter { !it.startsWith(":") }.joinToString("")
        val func = "fun $typeParams abcq(a: $typeWithoutBounds){}"
        val prop = "val abcq1: $typeWithoutBounds = TODO()"
        val newFile = Factory.psiFactory.createFile("$prefix\n\n$prop\n\n$func\n\n${fileCopy.text}")
        //Search for package of type
        UsageSamplesGeneratorWithStLibrary.findPackageForType(type)?.let { pack ->
            val import = Factory.psiFactory.createImportDirective(ImportPath(FqName(pack.fqName.toUnsafe()), true))
            newFile.addImport(import)
        }
        val ctx = PSICreator.analyze(newFile) ?: return null
        val psiFunc = newFile.getAllPSIChildrenOfType<KtNamedFunction>().firstOrNull() ?: return null
        val psiProp = newFile.getAllPSIChildrenOfType<KtProperty>().firstOrNull() ?: return null
        val propType = psiProp.typeReference?.getAbbreviatedTypeOrType(ctx)
        if (propType != null && !propType.isErrorType())  return propType
        return psiFunc.valueParameters.firstOrNull()?.typeReference?.getAbbreviatedTypeOrType(ctx)
    }

    fun calculateTypeArgs(type: String): Set<String> =
        if (!type.contains('<')) setOf()
        else type
            .split(",")
            .map { it.substringAfterLast('<').substringBefore('>').trim() }
            .filter { it != "*" }
            .toSet()


    fun recreateType(file: KtFile, type: KotlinType): KotlinType? = createType(file, type.getNameWithoutError())
    fun recreateType(file: KtFile, type: KtTypeReference): KotlinType? = createType(file, type.text)

}