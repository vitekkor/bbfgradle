// Original bug: KT-31290

package problem.over.here

// innocent functions :)
fun foo(a: List<String>) {}
fun foo(a: Int) {}

fun main() {
    // doesn't work with error: overload resolution ambiguity
    foo(listOf("a").mapTo(mutableListOf()) { it })

    // works
    val x = listOf("a").mapTo(mutableListOf()) { it }
    foo(x)
    foo({listOf("a").mapTo(mutableListOf()) { it }}())

    // The type parameters below are show as useless in IDEA, but removing them leads to the above issue
    foo(listOf("a").mapTo(mutableListOf()) { it } as MutableList<String>)
    foo(listOf("a").mapTo(mutableListOf<String>()) { it })
}
