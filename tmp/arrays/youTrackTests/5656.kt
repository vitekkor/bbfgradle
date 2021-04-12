// Original bug: KT-33162

interface A {
    fun foo1(callback: (a: Int)-> Unit)
}

class AImpl : A {
    override fun foo1(callback: (a: Int)-> Unit) { return }
    fun foo2(callback: (a: Int)-> Unit) { return }
}

class ADelegate(a : A) : A by a

class B {
    val a = ADelegate(AImpl())

    fun b1() {
        a.foo1 {
            a.runCatching { }.onFailure { return@foo1 }
        }
    }

    fun b2() {
        (a as AImpl).foo2 {
            a.runCatching { }.onFailure { return@foo2 }
        }
    }
}
