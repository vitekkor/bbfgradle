// Original bug: KT-2822

open class A {
    open fun foo(): Int = 1
}

fun f() {
            object : A() {
                override fun foo(): Int {
                    return super<A>.foo()
                }
            }
}
