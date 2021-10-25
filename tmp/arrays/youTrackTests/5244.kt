// Original bug: KT-32618

fun foo(): Int = 42

@Deprecated(message = "Use top-level foo instead", level = DeprecationLevel.WARNING, replaceWith = ReplaceWith("foo()"))
fun Int.foo(): Int = 42

fun caller() {
    with(239) {
        foo() // Depecated overload is selected, "replace with" quickfix does nothing
    }
}
