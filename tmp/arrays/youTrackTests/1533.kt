// Original bug: KT-16471

fun test(s: String?) =
        when (s?.length) {
            null -> "null" 
            0 -> "empty"
            1 -> "one"
            2 -> "two"
            else -> "many"
        }
