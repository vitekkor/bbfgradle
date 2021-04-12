// Original bug: KT-11119

class A {
    fun f(x: Boolean) {
    }

    fun f(y: String) {
    }
}

class B {
    
    fun f() {
        var a: A? = null
        a = A()
        a.f(true) // works
    }
}
