// Original bug: KT-32800

import kotlin.reflect.KProperty

open class A
class A1 : A()
class A2 : A()

open class B(val size: Int)
class B1(size: Int) : B(size)
class B2(size: Int) : B(size)

class ADelegate {
    inline operator fun <reified T : A> getValue(obj: Any, property: KProperty<*>): T = when (T::class) {
        A1::class -> A1() as T
        A2::class -> A2() as T
        else -> throw IllegalStateException()
    }
}

class BDelegate(val size: Int) {
    inline operator fun <reified T : B> getValue(obj: Any, property: KProperty<*>): T = when (T::class) {
        B1::class -> B1(size) as T
        B2::class -> B2(size) as T
        else -> throw IllegalStateException()
    }
}

class DelegateTest {
    val arg1: A1 by ADelegate()
    val arg2: B1 by BDelegate(1)
    val arg3: A1 by BDelegate(1)//Error here
    val arg4: B1 by ADelegate()//and here
}

