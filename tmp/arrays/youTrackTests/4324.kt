// Original bug: KT-18223

inline fun ifNotNull(a: Any?) {
    if (a != null) {
        println("not null")
    }
}

fun test() {
    val a: String = System.getProperty("zzz")
    ifNotNull(a)
}
