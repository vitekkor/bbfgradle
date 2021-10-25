// Original bug: KT-21043

suspend fun <T> foo(v: T): T = v

inline suspend fun boo(v: String): String {
    foo("!$v")
    return foo(v)
}

inline suspend fun bar(v: String) {
    val x = boo(v)
    println("bar($x)")
}
