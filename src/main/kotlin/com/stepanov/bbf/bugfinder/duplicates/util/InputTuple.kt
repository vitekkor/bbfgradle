//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.util

data class InputTuple(
        val T1: DeltaTreeElement,
        val T2: DeltaTreeElement,
        val relation: BinaryRelation<DeltaTreeElement>
)