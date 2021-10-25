// Original bug: KT-5258

fun test1(a: Int) =
        when (a) {
            0 -> "0"
            1 -> "1"
            else -> "hz"
        }


fun test2(a: Int) =
        when {
            a == 0 -> "0"
            a == 1 -> "1"
            else -> "hz"
        }

