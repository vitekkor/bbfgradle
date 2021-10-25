// Original bug: KT-45216

package com.thrain.cets9.rules.rules.caracteristiques

class TestInBetween {
    fun validate() {
        val (min: Int?, max: Int?) = minMax(1)
        if (min == null || max == null || 1 in min..max) error("")
    }

    private fun minMax(value: Int): Pair<Int?, Int?> {
        return Pair(4, 40)
    }
}
