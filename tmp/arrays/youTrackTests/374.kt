// Original bug: KT-41143

// MODULE: lib1
// FILE: lib1.kt
object DelegateTest {
    var result = ""

    val f by lazy {
        object { }.also { result += "OK" }
    }

    fun bbb() = f
}

// MODULE: lib2(lib)
// FILE: lib2.kt

fun test3(): String {
    DelegateTest.bbb()
    return DelegateTest.result
}
