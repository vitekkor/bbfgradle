// Original bug: KT-10976

open class Bar {}

fun <T> foo(v: T) = Unit
fun <T : Bar> foo(v: T) = Unit

fun test() {
    foo("string") // OK
    foo(Bar()) // OK
    foo<Bar>(Bar()) // OK
    foo<String>("string") // Error: Type argument is not within its bounds: should be subtype of 'Bar'
}
