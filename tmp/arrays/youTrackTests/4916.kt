// Original bug: KT-35201

fun <T> foo /* aka  foo1 */(t: T, r: String? = null, a: T.() -> Unit): Unit = TODO()
fun <R> foo /* aka  foo2 */(r: String? = null, a: () -> R): Unit = TODO()

fun test() {
    foo(1) {}    // calls foo1
    foo {}       // calls foo2
    foo("a", {}) // calls foo2, suggests to move lambda out of parentheses which would make it ambiguous
}
