//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang

abstract class LangCfg {
    abstract val contextManager: ContextManager
    abstract val uniqueInternalsNames: List<String>
    abstract val fastMatcherEqualParameter: Double
    abstract val similarityBoundaryCoefficient: Double
    abstract val strongSimilarityBoundaryPercentage: Double
}