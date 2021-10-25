// Original bug: KT-19340

package test.nullabilityOnClassBoundaries

class Item {
    private var s1: String? = null
    private var s2: String? = null

    operator fun set(s1: String, s2: String) {
        this.s1 = s1
        this.s2 = s2
    }
}
