// Original bug: KT-24884

import kotlin.reflect.KMutableProperty0

class TestClass {
    public var propPublic: Int? = null
    internal var propInternal: Int? = null
    protected var propProtected: Int? = null
    private var propPrivate: Int? = null

    fun testAccessable() {
        println(this::propPublic is KMutableProperty0)    // true
        println(this::propInternal is KMutableProperty0)  // true
        println(this::propProtected is KMutableProperty0) // false
        println(this::propPrivate is KMutableProperty0)   // true
    }
}

fun main(args: Array<String>) {
    TestClass().testAccessable()
}
