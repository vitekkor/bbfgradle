//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.engine.matching

import com.stepanov.bbf.bugfinder.duplicates.util.DeltaTreeElement

interface Matcher {
    fun DeltaTreeElement.label() = this@label.type
    fun DeltaTreeElement.value() = this@value.text
}