// Original bug: KT-21780

interface A {
    val myVal: Boolean
    var myVar: Boolean
}

class X : A {
    override val myVal: Boolean
        get() = false
    override var myVar: Boolean  = false
        set(value) { } //warning:redundant setter
}
