// Original bug: KT-21164

interface A {
     fun c(): Int
}

interface B {
    fun c(): Int = 0
}

class C : A, B {
    // line reported as redundant override
    override fun c(): Int = super.c()
}
