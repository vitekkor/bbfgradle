package com.stepanov.bbf.reduktor.passes

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.executor.backends.CommonBackend
import com.stepanov.bbf.reduktor.util.TaskType
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.startTasksAndSaveNewFiles
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.isSubpackageOf
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.resolve.ImportPath
import java.util.*


class PreliminarySimplification(private val ktFile: KtFile, private val projPath: String, private val checker: CompilerTestChecker) {
    fun computeSlice(files: List<PsiFile> = listOf()) {
        //Get all imports of file
        //Map from node to import level

        val importsMap = linkedMapOf<PsiFile, Int>()
        val fileImports = ImportsGetter().getAllImportsFromFile(ktFile).toHashSet()
        var level = 1
        for (f in files.map { it as KtFile }) {
            f.beforeAstChange()
            starsExplosion(f.importDirectives, files)
        }
        while (true) {
            val oldImportsSize = importsMap.size
            for (f in files) {
                val kotFile = f as KtFile
                if (f == ktFile)
                    continue
                kotFile.beforeAstChange()
                if (kotFile.importDirectives.any { it -> fileImports.any { it1 -> it.text == it1.text } }
                        && !importsMap.containsKey(f)) {
                    importsMap[f] = level
                }
            }
            for (i in importsMap) {
                if (i.value == level)
                    ImportsGetter().getAllImportsFromFile(i.key as KtFile).forEach { fileImports.add(it) }
            }
            if (oldImportsSize == importsMap.size)
                break
            ++level
        }
        level -= 1
        //Start to transform all needed files
        val transformedFiles = mutableListOf<KtFile>()
        val filesToTransform = mutableListOf<KtFile>()
        for (i in importsMap.keys.reversed()) {
            if (importsMap[i] == level) {
                filesToTransform.add(i as KtFile)
            } else {
                //Start transformations
                startTasksAndSaveNewFiles(
                    filesToTransform,
                    projPath,
                    TaskType.TRANSFORM,
                    checker
                )
                filesToTransform.forEach { transformedFiles.add(it) }
                --level
                filesToTransform.clear()
                filesToTransform.add(i as KtFile)
            }
        }
        //Last level
        startTasksAndSaveNewFiles(
            filesToTransform,
            projPath,
            TaskType.TRANSFORM,
            checker
        )
        filesToTransform.forEach { transformedFiles.add(it) }
        filesToTransform.clear()

        //Transform files from current package
        val packageFqName = ktFile.packageFqNameByTree
        files
                .map { it as KtFile }
                .filter { !transformedFiles.contains(it) }
                .filter { it != ktFile }
                .forEach {
                    if (it.packageFqNameByTree.isSubpackageOf(packageFqName))
                        filesToTransform.add(it)
                }
        startTasksAndSaveNewFiles(
            filesToTransform,
            projPath,
            TaskType.TRANSFORM,
            checker
        )
    }



    //CAREFUL!! TERRIBLE SHIT CODE HERE!!
    fun starsExplosion(imports: List<KtImportDirective>, files: List<PsiFile>): List<KtImportDirective> {
        for (i in imports) {
            if (i.isAllUnder) {
                val fqName = i.importPath?.fqName ?: continue
                val newImports = HashSet<KtImportDirective>()
                for (f in files) {
                    if (f.firstChild !is KtPackageDirective)
                        break
                    val packageDirective = f.allChildren.first as KtPackageDirective
                    if (packageDirective.text.contains(fqName.asString())) {
                        getClasses(f as KtFile).mapTo(newImports) { getImport(it.fqName!!, ktFile) }
                        getObjects(f).mapTo(newImports) { getImport(it.fqName!!, ktFile) }
                        getFunctions(f)
                                .filter {
                                    it.getParentOfType<KtClass>(false) == null
                                            && it.getParentOfType<KtObjectDeclaration>(false) == null
                                }
                                .mapTo(newImports) { getImport(it.fqName!!, ktFile) }
                    }
                }
                if (newImports.isNotEmpty()) {
                    for (im in newImports) {
                        //TODO Check it
                        i.node.treeParent.addChild(im.node, null)
                        i.node.treeParent.addChild(KtPsiFactory(ktFile.project).createWhiteSpace("\n").node, null)
                    }
                    ktFile.node.removeChild(i.node)
                }
            }
        }
        return imports
    }

}

class ImportsGetter {

    fun createImportFromPackageDirective(file: KtFile): KtImportDirective? =
        file.packageDirective?.let {
            if (it.fqName.isRoot) null
            else getImport(it.fqName, file, true)
        }


    fun getAllImportsFromFile(file: KtFile): List<KtImportDirective> {
        val newImports = HashSet<KtImportDirective>()
        //Put old imports
        file.importDirectives.forEach { newImports.add(it) }
        val filterFun = {it: KtNamedDeclaration -> it.fqName != null}
        getImportableObj<KtClass>(file, filterFun).mapTo(newImports) { getImport(it.fqName!!, file) }
        getImportableObj<KtObjectDeclaration>(file, filterFun).mapTo(newImports) { getImport(it.fqName!!, file) }
        getImportableObj<KtProperty>(file) { it.parent is KtFile }.mapTo(newImports) { getImport(it.fqName!!, file) }
        getImportableObj<KtNamedFunction>(file, filterFun)
            .filter {
                it.getParentOfType<KtClass>(false) == null
                        && it.getParentOfType<KtObjectDeclaration>(false) == null
            }
            .mapTo(newImports) { getImport(it.fqName!!, file) }
        return newImports.toList()
    }

}

private inline fun <reified T : PsiElement> getImportableObj(file: KtFile, filterFunc: (T) -> Boolean): List<T> {
    val privateModifier = KtTokens.MODIFIER_KEYWORDS_ARRAY.find { it.value == "private" }!!
    //Filter private constructions
    return file.getAllPSIChildrenOfType<T>()
        .filterNot { (it as? KtModifierListOwner)?.hasModifier(privateModifier) ?: false }
        .filter { filterFunc(it) }
}

private fun getClasses(file: KtFile): List<KtClass> =
    file.getAllPSIChildrenOfType<KtClass>().filter { it.fqName != null }

private fun getObjects(file: KtFile): List<KtObjectDeclaration> =
    file.getAllPSIChildrenOfType<KtObjectDeclaration>().filter { it.fqName != null }

private fun getFunctions(file: KtFile): List<KtNamedFunction> =
    file.getAllPSIChildrenOfType<KtNamedFunction>().filter { it.fqName != null }

private fun getTopLevelProps(file: KtFile): List<KtProperty> =
    file.getAllPSIChildrenOfType<KtProperty>().filter { it.parent is KtFile }

private fun getImport(fqName: FqName, ktFile: KtFile, isAllUnder: Boolean = false): KtImportDirective =
    KtPsiFactory(ktFile.project).createImportDirective(ImportPath(fqName, isAllUnder))



