// Original bug: KT-1584

class A {
    fun foo() {}
}

class B {
    fun foo() {}

    fun A.bar() {
        //is resolved to a function 'A:foo', but it might be an ambiguity, because both are members
        foo()
    }
}
