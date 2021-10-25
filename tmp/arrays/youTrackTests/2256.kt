// Original bug: KT-42373

fun box(): String {
    val obj = object {
        val end = "K"

        fun foo() = Some("O").bar()

        inner class Some(s: String) : Base(s) {
            fun bar() = s + end
        }

        open inner class Base(val s: String) // This class has no parent in FIR2IR
    }
    return obj.foo()
}
