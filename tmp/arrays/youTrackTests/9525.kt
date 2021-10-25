// Original bug: KT-12915

class C {
    companion object {
        @JvmStatic
        fun foo(x: Int = 42) = "OK"
    }
}

fun box(): String {
    val v = C.Companion::foo
    return v.callBy(mapOf(v.parameters.first() to C.Companion))
}
