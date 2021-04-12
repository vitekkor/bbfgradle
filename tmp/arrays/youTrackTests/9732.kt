// Original bug: KT-3860

interface Some
interface Other: Some

fun Some.foo(action: () -> Unit) = 12
fun <T> Other.foo(action: ()-> T) = 12

fun test(other: Other) {
    other.foo { "hello" } // Resolved to Some.foo
    other.foo<String> { "hello" } // Resolved to Other.foo
}
