// Original bug: KT-30293

fun <K> select(x: K, y: K): K = TODO()

fun test() {
    val d = select("", 1)
    // type of d in IDEA is {Comparable<{String & Int & Byte & Short & Long}> & java.io.Serializable}
}
