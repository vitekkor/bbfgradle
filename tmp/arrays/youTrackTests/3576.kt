// Original bug: KT-38583

fun String.foo() {}

@Deprecated(replaceWith = ReplaceWith("this.foo()"), message = "Use foo()")
operator fun String.invoke() = foo()

fun main() {
    "abc"() // Deprecated usage of "invoke" operator
}
