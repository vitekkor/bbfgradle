package com.stepanov.bbf.bugfinder.mutator.metamorphicTransformations

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.RandomTypeGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperClassifiers
import org.jetbrains.kotlin.types.replace
import org.jetbrains.kotlin.types.typeUtil.asTypeProjection
import org.jetbrains.kotlin.types.typeUtil.isAnyOrNullableAny
import java.io.File
import kotlin.random.Random

class AddDeadCodeTransformation : MetamorphicTransformation() {

    private lateinit var rig: RandomInstancesGenerator
    override fun transform(
        mutationPoint: PsiElement,
        scope: HashMap<Variable, MutableList<String>>,
        expected: Boolean
    ) {
        rig = RandomInstancesGenerator(file as KtFile, ctx!!)
        for (i in 1..Random.nextInt(1, 10)) {
            AddVariablesToScope().transform(mutationPoint, scope, false)
        }

        for (i in 1..Random.nextInt(1, 10)) {
            createThrow(mutationPoint)
        }

        removeMutation(AddDeadCodeTransformation::class)
        AddIf().apply {
            transform(mutationPoint, scope, false)
            transform(mutationPoint, scope, false)
        }

        addAfterReturn(scope)

        println()
    }

    private fun addAfterReturn(scope: HashMap<Variable, MutableList<String>>) {
        val returnExpr = file.getAllPSIChildrenOfType<KtReturnExpression>().randomOrNull()
        val funExpr = file.getAllPSIChildrenOfType<KtNamedFunction>().randomOrNull()
        val throwExpr = file.getAllPSIChildrenOfType<KtThrowExpression>().randomOrNull()
        val expr = mutableListOf(returnExpr, funExpr, throwExpr).filterNotNull().randomOrNull() ?: return

        when (expr) {
            is KtReturnExpression, is KtThrowExpression -> {
                if (Random.getTrue(100))
                    addRandomNodes(expr)
                if (Random.getTrue(55)) {
                    val all = getAllMutations()
                    for (i in 0..Random.nextInt(3)) {
                        executeMutations(expr, scope, false, all)
                    }
                } else {
                    for (i in 0..Random.nextInt(100)) {
                        val randomLines = getRandomLinesFromFile() ?: continue
                        try {
                            addAfterMutationPoint(expr) { it.createExpression(randomLines) }
                        } catch (_: Exception) {
                        }
                    }
                }
            }

            is KtNamedFunction -> {
                val result = createReturn(expr)
                result?.let {
                    if (Random.getTrue(100))
                        addRandomNodes(it)
                    if (Random.getTrue(55)) {
                        val all = getAllMutations()
                        for (i in 0..Random.nextInt(3)) {
                            executeMutations(expr, scope, false, all)
                        }
                    } else {
                        for (i in 0..Random.nextInt(100)) {
                            val randomLines = getRandomLinesFromFile() ?: continue
                            try {
                                addAfterMutationPoint(it) { factory -> factory.createExpression(randomLines) }
                            } catch (_: Exception) {
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRandomLinesFromFile(): String? {
        val begin = Random.nextInt(file.text.split("\n").size - 2)
        val end = Random.nextInt(begin + 1, file.text.split("\n").size - 1)
        val nodesBetweenWhS = file.getNodesBetweenWhitespaces(begin, end)
        if (nodesBetweenWhS.isEmpty() || nodesBetweenWhS.all { it is PsiWhiteSpace }) return null

        return nodesBetweenWhS.filter { it.parent !in nodesBetweenWhS }.joinToString("", "kotlin.run {\n", "\n}") {
            if (it is KtProperty) {
                Factory.psiFactory.createProperty(
                    "deadcode_${it.name}",
                    it.getPropertyType(ctx!!).toString(),
                    Random.nextBoolean(),
                    it.initializer?.text
                ).text
            } else it.text
        }
    }

    private fun createReturn(expr: KtNamedFunction): PsiElement? {
        var returnValue = expr.getReturnType(ctx!!)?.let { rig.generateValueOfType(it) } ?: return null
        if (returnValue == "{}") returnValue = ""
        val result = expr.bodyExpression?.lastChild?.prevLeaf()?.let { mp ->
            checker.addNodeIfPossibleWithNode(mp, Factory.psiFactory.createExpression("return $returnValue"))
        }
        if (result == expr.lastChild.prevSibling) return null
        return result
    }

    private fun getAllMutations(): MutableList<Pair<MetamorphicTransformation, Int>> {
        val old = defaultMutations
        restoreMutations()
        val all = defaultMutations
        all.map {
            if (it !in old) {
                removeMutation(it.first::class)
            }
            it.first to it.second / 2
        }
        all.removeIf { it.first is AddDeadCodeTransformation }
        return all
    }

    private fun createThrow(mutationPoint: PsiElement) {
        val randomException = throwables.random()
        val realTypeParams = randomException.typeConstructor.parameters.map {
            RandomTypeGenerator.generateRandomTypeWithCtx(it.upperBounds.firstOrNull()) ?: DefaultKotlinTypes.intType
        }
        val typeToThrow = randomException.defaultType.replace(realTypeParams.map { it.asTypeProjection() })
        val expressionToThrow = rig.generateValueOfTypeAsExpression(typeToThrow) ?: return
        val randomType = randomException.getAllSuperClassifiers().filter {
            !it.defaultType.isAnyOrNullableAny() && it.getAllSuperClassifiers()
                .map { it.name.asString() }.contains("Throwable")
        }.toList().random().name.asString()
        addAfterMutationPoint(mutationPoint) {
            it.createExpression("try{\n${expressionToThrow.text}\n}catch(e: $randomType){}")
        }
    }

    private val throwables = StdLibraryGenerator.klasses
        .filter {
            it.getAllSuperClassifiersWithoutAnyAndItself()
                .map { it.name.asString() }.contains("Throwable")
        }.filterDuplicatesBy { it.name }

    private fun addRandomNodes(returnExpression: PsiElement) {
        val randConst = Random.nextInt(numOfTries.first, numOfTries.second)
        val nodes = file.node.getAllChildrenNodes().filter { it.elementType !in NodeCollector.excludes }
        val node = file.node.getAllChildrenNodes()
            .filter { it.elementType == KtTokens.RETURN_KEYWORD && it.treeParent.text == returnExpression.text }
            .map { it.treeParent }.firstOrNull() ?: return
        log.debug("Trying to change some nodes to nodes from other programs $randConst times")
        for (i in 0..randConst) {
            log.debug("Try №$i of $randConst")
            val randomNode = getRandomNode(nodes)

            if (randomNode.psi is KtNamedFunction && randomNode.text.contains("fun box")) continue

            val line = File("database.txt").bufferedReader().lines()
                .filter { it.takeWhile { it != ' ' } == randomNode.elementType.toString() }.findFirst()
            if (!line.isPresent) continue
            val files = line.get().dropLast(1).takeLastWhile { it != '[' }.split(", ")
            val randomFile = files.random()
            val psi = Factory.psiFactory.createFile(File("${CompilerArgs.baseDir}/$randomFile").readText())
            val targetNode = psi.node.getAllChildrenNodes()
                .filter { it.elementType == randomNode.elementType && it.text.isNotBlank() }.randomOrNull() ?: continue
            //if (targetNode.psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().isNotEmpty()) continue
            if (targetNode.psi is KtConstantExpression) continue
            checker.addAfter(node.psi, targetNode.psi)
        }
    }

    private fun getRandomNode(nodes: List<ASTNode>): ASTNode {
        var randomNode = nodes[Random.nextInt(0, nodes.size - 1)]
        while (randomNode.text == "\"\"" || randomNode.text == "")
            randomNode = nodes[Random.nextInt(0, nodes.size - 1)]
        return randomNode
    }


    private val numOfTries: Pair<Int, Int> by lazy { if (checker.project.files.size == 1) 500 to 1000 else 2000 to 4000 }
}