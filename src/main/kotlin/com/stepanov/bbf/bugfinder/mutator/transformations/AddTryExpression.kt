package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.psiFactory
import com.stepanov.bbf.bugfinder.util.getTrueWithProbability
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.psi.KtExpression
import kotlin.random.Random

class AddTryExpression: Transformation() {
    override fun transform() {
        file.getAllPSIChildrenOfType<KtExpression>().filter { getTrueWithProbability(15) }.forEach { exp ->
            val newExp = when (Random.nextInt(0, 3)) {
                0 -> psiFactory.createExpression("try {\n${exp.text}\n} catch(e: Exception){}")
                1 -> psiFactory.createExpression("try {\n${exp.text}\n} catch(e: Exception){}\nfinally{}")
                else -> {
                    val randomExp = file.getAllPSIChildrenOfType<KtExpression>().random()
                    psiFactory.createExpression("try {\n${exp.text}\n} catch(e: Exception){}\nfinally{\n${randomExp.text}\n}")
                }
            }
            checker.replaceNodeIfPossible(exp.node, newExp.node)
        }

    }

}