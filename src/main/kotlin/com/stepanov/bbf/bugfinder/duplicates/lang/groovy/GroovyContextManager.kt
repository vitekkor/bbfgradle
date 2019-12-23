//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang.groovy

import com.stepanov.bbf.bugfinder.duplicates.lang.ContextLevel
import com.stepanov.bbf.bugfinder.duplicates.lang.ContextLevelChange
import com.stepanov.bbf.bugfinder.duplicates.lang.ContextManager
import com.stepanov.bbf.bugfinder.duplicates.util.DeltaTreeElement


class GroovyContextManager : ContextManager() {
    override val initialContextLevel: ContextLevel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val possibleContextLevelChanges: List<ContextLevelChange>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val nodesIncompatibilityConditions: List<(DeltaTreeElement, DeltaTreeElement) -> Boolean>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun getNewContext(currentNode: DeltaTreeElement, currentContextLevel: ContextLevel): ContextLevel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}