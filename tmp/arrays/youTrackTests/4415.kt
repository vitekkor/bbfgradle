// Original bug: KT-36475

class B {
    val foo: String.() -> Unit = {} // (1)
    fun String.foo(): Unit {} // (2)
    fun bar() {
        "1".foo() // resolves to (2)
        val str = "1"
        with("2") {
            foo()      //resolves to (2) 
            this.foo() //resolves to (2)
        }
        "".run {
            foo()      //resolves to (2)
            this.foo() //resolves to (2)
        }
        "".apply {
            foo()      //resolves to (2)
            this.foo() //resolves to (2)
        }
        "".also {
            it.foo()   //resolves to (2)
        }
        "".let {
            it.foo()   //resolves to (2)
        }
    }
}
