// Original bug: KT-1584

class G

class H {
    fun foo() {}

    fun G.foo() {}

    fun G.bar() {
        //is resolved to function 'H:G.foo', but should be an ambiguity
        foo()
    }
}
