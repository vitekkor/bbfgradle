//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang

import com.stepanov.bbf.bugfinder.duplicates.util.DeltaTreeElement


typealias ContextLevelChange = Pair<ContextLevel, ContextLevel>

infix fun ContextLevel.to(that: ContextLevel) = Pair(this, that)

abstract class ContextManager {
    abstract val initialContextLevel: ContextLevel
    abstract val possibleContextLevelChanges: List<ContextLevelChange>
    abstract val nodesIncompatibilityConditions: List<(DeltaTreeElement, DeltaTreeElement) -> Boolean>

    abstract fun getNewContext(currentNode: DeltaTreeElement, currentContextLevel: ContextLevel) : ContextLevel

    fun contextsAreCompatible(l1: ContextLevel, l2: ContextLevel)
            = possibleContextLevelChanges.contains(l1 to l2)

    fun nodesAreCompatible(node1: DeltaTreeElement, node2: DeltaTreeElement)
            = nodesIncompatibilityConditions.all { !it(node1, node2) }
}