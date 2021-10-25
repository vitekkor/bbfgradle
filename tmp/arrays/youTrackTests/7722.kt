// Original bug: KT-21237

class A {
    @Deprecated("msg", ReplaceWith("new"))
    var old
        get() = new
        set(value) {
            new = value
        }
    var new = ""
}

fun foo() {
    A().apply {
        old = "foo" //Invoke 'Replace with 'new' here
    }
}
