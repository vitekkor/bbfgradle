// Original bug: KT-24114

fun bar(x: Int): Int {
    return x
}

fun foo(x: Int): Int {
    while (true) {
        when (x) {
            1 -> {
                val i = bar(x)
                when (i) {
                    1 -> return 2
                    2 -> return 3
                }
            }
            else -> error("zzz")
        }
    }
}
