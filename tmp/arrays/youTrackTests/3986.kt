// Original bug: KT-18192

interface I1

interface I2<T : I1>

class SomeClass<T : I1, out U : I2<T>>

open class Impl1<T : I1> : I2<T> {
    fun test(): SomeClass<T, Impl1<T>> = SomeClass()
}
open class Impl2<T : I1> : I2<T> {
    fun test(): SomeClass<T, Impl2<T>> = SomeClass()
}
open class Impl3<X : Any, T : I1> : I2<T> {
    fun test(): SomeClass<T, Impl3<X, T>> = SomeClass()

}

class TestI1 : I1 {

    fun test1(): List<I2<TestI1>> = listOf(O1, O2, O3)

    fun test2(): List<SomeClass<TestI1, I2<TestI1>>> = listOf(
        O1.test(),
        O2.test(),
        O3.test()
    )

    fun test3(): SomeClass<TestI1, I2<TestI1>> = when {
        true -> O1.test()
        false -> O2.test()
        else -> O3.test()
    }

    object O1 : Impl1<TestI1>()
    object O2 : Impl2<TestI1>()
    object O3 : Impl3<Any, TestI1>()
}
