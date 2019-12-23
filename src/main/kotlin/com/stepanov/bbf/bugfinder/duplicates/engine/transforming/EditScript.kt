//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.engine.transforming

data class EditScript(val editOperations: MutableList<EditOperation> = mutableListOf()) {

    fun addAndPerform(operation: EditOperation) {
        if (operation.isValid()) editOperations.add(operation)
        operation.perform()
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        editOperations.forEach {
            stringBuilder.append(it).append("\n")
        }
        return stringBuilder.toString()
    }
}