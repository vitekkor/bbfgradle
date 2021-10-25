// Original bug: KT-30179

interface A
interface B : A {
    val field: Int // adding new field in child interface
}

interface ValueHolder {
    val value: Int
}

open class AImpl(val holder: ValueHolder) // passing value holder to parent interface impl class constructor
class BImpl(holder: ValueHolder) : AImpl(holder), B {
    override val field: Int // implementing new field of B interface
        get() = holder.value
}
