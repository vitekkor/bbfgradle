// Original bug: KT-18164

inline fun Any?.zap() {
    if (this != null) println("not null")
}

fun test() {
    val heh = System.getProperty("heh")
    val z = heh!!
    heh.zap() // (*)
}
