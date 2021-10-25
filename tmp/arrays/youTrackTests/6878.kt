// Original bug: KT-25974

fun test(a: UInt) =
        when (a) {
            0u -> "zero"
            1u -> "one"
            2u -> "two"
            3u -> "three"
            else -> "other"
        }
