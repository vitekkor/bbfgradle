// Original bug: KT-26291

package test

interface IFoo<T> {
    fun foo(): T
}

open class CFooInt : IFoo<Int> {
    override fun foo(): Int = 42
}

open class CFooUInt : IFoo<UInt> {
    override fun foo(): UInt = 42u
}
