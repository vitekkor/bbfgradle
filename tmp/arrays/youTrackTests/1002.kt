// Original bug: KT-45300

open class SuperA {
    fun foo() {

    }
}

class A : SuperA() {

    inline fun test() {
        super<SuperA>.foo()
    }

    inline fun test2() {
        {
            super<SuperA>.foo()
        } ()
    }

    inline fun test3() {
        object : SuperA() {
            fun test() {
                super<SuperA>@A.foo()
            }
        }.test()
    }
}
