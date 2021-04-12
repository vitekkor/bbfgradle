// Original bug: KT-30733

fun main() {
    with (C()) {
        foo("hello")
    }
}

class C() {
    var x = 3

    fun foo(a: String) {
        block {
            //breakpoint here
            val something = 4
        }
    }

    fun block(block: () -> Unit) {
        block()
    }
}
