package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsageSamplesGeneratorWithStLibrary
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
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
        val classDef =
            splitTypeByCommas(type)
                .mapIndexed { index, s -> if (!s.contains(':')) "AUX$index: $s" else s }
                .joinToString(prefix = "${type.substringBefore('<')}<", postfix = ">")
        val klass =
            try {
                Factory.psiFactory.createClass("class $classDef")
            } catch (e: Exception) {
                null
            }
        var typeParams = ""
        var typeParamsWithoutBounds = ""
        if (klass == null || klass.name == null) {
            val typeReference = Factory.psiFactory.createTypeIfPossible(type) ?: return null
            typeParams =
                typeReference.getAllChildren()
                    .filter { it is KtUserType && !it.text.contains('<') }
                    .map { it.text }
                    .filter { ta -> file.getAllPSIChildrenOfType<KtClass>().all { it.name != ta } }
                    .filter { ta -> UsageSamplesGeneratorWithStLibrary.findPackageForType(ta) == null }
                    .let { if (it.isEmpty()) "" else it.joinToString(prefix = "<", postfix = ">") }
            typeParamsWithoutBounds = type
        } else {
            typeParams =
                klass.typeParameterList?.parameters
                    ?.filter { it.text.trim().isNotEmpty() }
                    ?.filterNot { it.name?.contains("AUX") == true }
                    ?.filter { ta -> file.getAllPSIChildrenOfType<KtClass>().all { it.name != ta.name } }
                    ?.filter { ta -> UsageSamplesGeneratorWithStLibrary.findPackageForType(ta.text) == null }
                    ?.let { if (it.isEmpty()) "" else it.joinToString(prefix = "<", postfix = ">") { it.text } } ?: ""
            typeParamsWithoutBounds =
                klass.typeParameterList?.parameters
                    ?.let {
                        if (it.isEmpty()) ""
                        else it.joinToString(prefix = "<", postfix = ">") {
                            if (it.text.contains("AUX")) it.text.substringAfter(':')
                            else it.text.substringBefore(':')
                        }
                    }
                    ?: ""
            if (typeParams.trim().isEmpty()) typeParams = typeParamsWithoutBounds
        }
        val n = klass?.name ?: ""
        val func = "fun $typeParams abcq(a: $n$typeParamsWithoutBounds){}"
        val prop = "val abcq1: $n$typeParamsWithoutBounds = TODO()"
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
        if (propType != null && !propType.isErrorType()) return propType
        return psiFunc.valueParameters.firstOrNull()?.typeReference?.getAbbreviatedTypeOrType(ctx)
    }


    private fun splitTypeByCommas(type: String): List<String> {
        val typeArgs = type.substringAfter('<').substringBeforeLast('>')
        if (typeArgs == type || typeArgs.isEmpty()) return listOf()
        val splitTypes = mutableListOf<String>()
        val handledType = StringBuilder()
        var brackets = 0
        for (ch in typeArgs) {
            if (ch == ',' && brackets == 0) {
                splitTypes.add(handledType.toString())
                handledType.clear()
            } else {
                if (ch == '<') brackets++
                if (ch == '>') brackets--
                handledType.append(ch)
            }
        }
        splitTypes.add(handledType.toString())
        return splitTypes
    }

    fun recreateType(file: KtFile, type: KotlinType): KotlinType? = createType(file, type.getNameWithoutError())
    fun recreateType(file: KtFile, type: KtTypeReference): KotlinType? = createType(file, type.text)

}