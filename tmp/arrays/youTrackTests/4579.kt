// Original bug: KT-35064

object Foo {
    fun <T, R> test(a: kotlin.reflect.KFunction1<T, R>): R = TODO()
    fun <T, R> test(a: kotlin.reflect.KFunction2<T, R, Unit>) {}
}

open class Other
fun Other.bar() {} // is resolved here in the OI

open class Sub : Other()
fun Sub.bar() {}

fun test() {
    Foo.test(Other::bar) // overload resolution ambiguity in the NI, OK in the OI
}
