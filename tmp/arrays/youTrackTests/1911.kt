// Original bug: KT-40128

fun bar() {} // (1)

object Scope {
    fun foo(f: () -> Unit) {}

    fun bar(x: Int = 0) {} // (2)

    fun test() {
        val r1 = foo(::bar) // warning, ::bar is resolved to (1)
        val r2 = foo(Scope::bar) // OK, bar is resolved to Scope.bar 
    }
}
