// Original bug: KT-27260

class A(val x: String?) {
    fun foo(other: A) {
        when {
            x != null && other.x != null -> println("1")
            // False positive: Condition "x != null" is always false
            x != null -> println("2")
        }
    }
}

