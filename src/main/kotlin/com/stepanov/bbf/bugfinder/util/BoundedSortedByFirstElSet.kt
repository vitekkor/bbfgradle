package com.stepanov.bbf.bugfinder.util

import org.jetbrains.kotlin.psi.KtFile

class BoundedSortedByFirstElSet<T>(
    private val firstEl: T,
    private val bound: Int,
    //If same should return 0
    private val comparator: Comparator<T>
) {

    fun add(a: T) {
        if (a is KtFile && firstEl is KtFile) {
            if (a.text.trim() == firstEl.text.trim()) {
                return
            }
        }
        val c1 = comparator.compare(firstEl, a)
        for ((ind, el) in data.withIndex()) {
            if (c1 < el.second) {
                data.add(ind, a to c1)
                break
            }
            if (ind == data.size - 1) {
                data.add(a to c1)
                break
            }
        }
        if (data.size > bound) data.removeAt(data.size - 1)
    }

    val data = mutableListOf(firstEl to 0)
}