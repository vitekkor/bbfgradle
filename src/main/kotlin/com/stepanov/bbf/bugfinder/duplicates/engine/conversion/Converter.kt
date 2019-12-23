//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.engine.conversion

import com.stepanov.bbf.bugfinder.duplicates.engine.transforming.EditOperation
import com.stepanov.bbf.bugfinder.duplicates.engine.transforming.EditScript

data class OperationWithSelectedMark(val operation: EditOperation, var selected: Boolean)

object Converter {
    fun convert(editScript: EditScript) : List<DiffChunk> {
        val chunks = mutableListOf<DiffChunk>()
        val operationsWithSelectedMark = editScript.editOperations
                .map { OperationWithSelectedMark(it, false) }

        for (operationWithSelectedMark in operationsWithSelectedMark) {
            if (operationWithSelectedMark.selected) continue

            val chunk = DiffChunk()
            operationWithSelectedMark.selected = chunk.tryToMerge(operationWithSelectedMark.operation)

            operationsWithSelectedMark
                    .filter { !it.selected }
                    .forEach { it.selected = chunk.tryToMerge(it.operation) }

            chunk.initType()
            chunks.add(chunk)
        }

        return chunks
    }
}