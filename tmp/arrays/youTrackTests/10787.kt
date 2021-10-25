// Original bug: KT-1584

class E
fun E.foo() {}

class F {
    fun foo() {}

    fun E.bar() {
        //is resolved to 'E.foo', but 'F:foo' is a member, that is more privileged
        foo()
    }
}
