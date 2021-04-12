// Original bug: KT-15971

interface B {
    fun foo(x: Int = 42): Int
    
    fun bar(x: Int): Int
}

interface C {
    fun foo(x: Int): Int
    
    fun bar(x: Int = 33): Int
}

class D : B, C {
    override fun foo(x: Int) = x
    override fun bar(x: Int) = x
}
