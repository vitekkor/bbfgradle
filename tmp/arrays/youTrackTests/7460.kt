// Original bug: KT-26509

inline class S(val x: String) {
    private fun foo() = x

    companion object {
        fun bar(s: S) = s.foo()
    }
}

fun main(args: Array<String>) {
    println(S.bar(S("OK")))
}
