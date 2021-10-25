// Original bug: KT-36776

fun <T> materialize(): T = "foo" as T

fun foo(s: String?) {}

fun main(flag: Boolean) {
    foo(if (flag) materialize() else null) // materialize is inferred to Nothing?
}
