// Original bug: KT-41144

import FooBar.*

fun test() {
    val fooBar: FooBar = FOO
    foo(
        value = when (fooBar) { // [REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION] Redundant spread (*) operator
            FOO -> arrayOf(listOf(1))
            BAR -> arrayOf(listOf(1))
        }
    )
}

fun <T> foo(vararg value: List<T>) {}
enum class FooBar { FOO, BAR }
