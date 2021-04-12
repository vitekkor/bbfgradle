// Original bug: KT-1584

class C {
    fun D.foo() {}
}

class D {
    fun C.foo() {}

    fun C.bar() {
        //is resolved to 'D:C.foo' (declared in a class D), but it might be an ambiguity, because both are members
        foo()
    }
}
