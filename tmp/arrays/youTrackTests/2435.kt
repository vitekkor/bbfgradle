// Original bug: KT-40922

interface A {
    val s: String
    fun f()
}

interface B {
    val s: String
    fun f()
}

abstract class C {
    abstract val s: String
    abstract fun f()
}
