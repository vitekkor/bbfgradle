// Original bug: KT-13604

package test

open class Base {

    class A : Base() {
        companion object {
            val prop = 1
        }
    }

    companion object: List<Int> by listOf(A.prop)
}
