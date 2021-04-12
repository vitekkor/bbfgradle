// Original bug: KT-17362

open class A {
    companion object : A() {
        init {
            println("init A.Companion")
        }        
        fun foo() {
			println("foo")
        }
    }
    init {
        println("init A")
        foo()
    }
}
// A() prints
// init A
// foo
// init A.Companion
// init A
// foo
