// Original bug: KT-31198

fun foo() {}
fun bar(vararg foos: () -> Unit) {}

fun main() {
    bar(::foo, ::foo) // OK
    bar(*arrayOf(::foo, ::foo)) // OK in NI, CCE in OI. Warning: "Remove redundant spread operator"
    bar(*listOf(::foo, ::foo).toTypedArray()) // CCE both in Old and New Inference
}
