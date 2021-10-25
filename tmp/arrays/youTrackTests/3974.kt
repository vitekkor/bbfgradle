// Original bug: KT-22012

open class A
class B : A()

class Matcher<T>

fun <T> assertThat(actual: T, matcher: Matcher<in T>) {}
fun <T> myIs(value: T): Matcher<T> = TODO()

fun test() {
    assertThat(A(), myIs(B()))
}
