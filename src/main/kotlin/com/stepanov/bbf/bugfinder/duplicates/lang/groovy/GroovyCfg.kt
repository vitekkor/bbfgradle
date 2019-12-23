//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang.groovy

import com.stepanov.bbf.bugfinder.duplicates.lang.ContextManager
import com.stepanov.bbf.bugfinder.duplicates.lang.LangCfg


class GroovyCfg : LangCfg() {
    override val contextManager: ContextManager
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val uniqueInternalsNames: List<String>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val fastMatcherEqualParameter: Double
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val similarityBoundaryCoefficient: Double
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val strongSimilarityBoundaryPercentage: Double
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}