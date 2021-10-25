// Original bug: KT-32343

    fun <T> foo(s: T): Int where T : String?, T : CharSequence =
        when (s) { // No null check
            "1" -> 1
            else -> 0
        }
    