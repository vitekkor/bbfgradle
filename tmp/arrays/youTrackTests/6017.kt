// Original bug: KT-29948

@DslMarker
annotation class MyDsl

@MyDsl
interface Foo<T> {
    val x: Int
}

val Foo<*>.bad: Int get() = x // ERROR: val value1 can't be called in this context by explicit receiver

fun Foo<*>.badFun(): Int = x // ERROR: same

val Foo<Int>.good: Int get() = x // OK

fun test(foo: Foo<*>) {
    foo.apply { 
        x // ERROR: same
    }
} 
