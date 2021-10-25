// Original bug: KT-45377

// FILE: a.kt

suspend fun test() {
    B().foo("Test")
}

// FILE: b.kt

private const val CONSTANT = "Test"

class B {
    suspend fun foo(s: String = CONSTANT) {
    }
}
