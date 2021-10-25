// Original bug: KT-21026

suspend fun sid(v: String): String = v

inline suspend fun foo(): String {
    println(sid("1"))
    println(sid("2"))
    return "OK"
}

suspend fun bar(): String {
    println("before")
    return foo()
}
