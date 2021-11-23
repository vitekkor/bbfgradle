package com.stepanov.bbf.bugfinder.util.instrumentation

import coverage.CoverageEntry
import org.objectweb.asm.tree.LdcInsnNode
import org.objectweb.asm.tree.MethodNode


fun MethodNode.getCoverageEntry(className: String): CoverageEntry {
    val parameters =
        this.desc.substringAfter('(')
            .substringBeforeLast(')')
            .split(";")
            .filter { it.trim().isNotEmpty() }
            .flatMap {
                if (it.startsWith("L")) {
                    listOf(it)
                } else {
                    val resList = mutableListOf<String>()
                    val iter = it.iterator()
                    while (iter.hasNext()) {
                        val ch = iter.nextChar()
                        if (ch == '[') {
                            val nextChar = iter.nextChar()
                            val arrayOfWhat = when (nextChar) {
                                'L' -> {
                                    var curChar = iter.next()
                                    val localRes = StringBuilder(curChar.toString())
                                    while (curChar != ';' && iter.hasNext()) {
                                        curChar = iter.nextChar()
                                        localRes.append(curChar)
                                    }
                                    localRes.toString()
                                }
                                else -> {
                                    CoverageEntry.jvmTypeToName["$nextChar"] ?: "Object"
                                }
                            }
                            resList.add("Array<${arrayOfWhat.substringAfterLast('/')}>")
                        } else if (ch == 'L') {
                            resList.add(it.substringAfter('L'))
                            break
                        } else {
                            CoverageEntry.jvmTypeToName["$ch"]?.let { resList.add(it) }
                        }
                    }
                    resList
                }
            }
            .joinToString(";") { it.substringAfterLast('/') }
            .ifEmpty { ";" }
    val returnValueType = this.desc.substringAfterLast(')').substringBeforeLast(';').substringAfterLast('/')
    return CoverageEntry(className, this.name, parameters, returnValueType)
}

object CoverageGuidingCoefficients {
    const val EPSILON = 80
    const val SCORES_FOR_BUG = 30

    //Sum of coverage entries
//    const val UCB_MULTIPLIER = 10.0
//    const val MCMC_MULTIPLIER = 0.04
//    const val SCORE_DIVIDER = 0.1

    //Different entries
    const val UCB_MULTIPLIER = 1.75
    const val MCMC_MULTIPLIER = 0.3
    const val SCORE_DIVIDER = 10.0
}