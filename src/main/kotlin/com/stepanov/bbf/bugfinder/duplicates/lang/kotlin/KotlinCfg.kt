//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang.kotlin

import com.stepanov.bbf.bugfinder.duplicates.lang.LangCfg

class KotlinCfg : LangCfg() {
    override val contextManager = KotlinContextManager()
    override val uniqueInternalsNames = listOf("import_list", "package_directive")
    override val fastMatcherEqualParameter = 0.9
    override val similarityBoundaryCoefficient = 1.1
    override val strongSimilarityBoundaryPercentage = 0.9
}