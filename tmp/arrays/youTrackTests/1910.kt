// Original bug: KT-40128

fun bar() {} // (1)

object Scope {
    fun foo(f: () -> Unit) {}

    fun bar(x: Int = 0) {} // (2)

    fun test() {
        val result = foo(::bar) // OK for OI as candidate (1) is fine
    }
}
