package com.stepanov.bbf.bugfinder.util

class BoundedSortedByFirstElSet<T>(private val firstEl: T, private val bound: Int, private val comparator: Comparator<T>) {

    fun add(a: T) {
        val c1 = comparator.compare(firstEl, a)
        for ((ind, el) in data.withIndex()) {
            if (comparator.compare(firstEl, el) > c1) {
                data.add(ind, a)
            }
        }
        if (data.size > bound) data.removeAt(data.size - 1)
    }

    val data = mutableListOf(firstEl)
}