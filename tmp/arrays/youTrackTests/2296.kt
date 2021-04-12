// Original bug: KT-28897

fun box(): String {
    val b = B()
    b.foo()
    b.bar()
    b.baz()

    if (r != "A.foo();A.bar();A.foo();C.baz();C.foo();") return "fail: $r"

    return "OK"
}

var r = ""

interface A {
    fun foo() {
        r += "A.foo();"
    }

    fun bar() {
        r += "A.bar();"
        foo()
    }
}

interface C {
    private fun foo() {
        r += "C.foo();"
    }

    fun baz() {
        r += "C.baz();"
        foo()
    }
}

class B : A, C
