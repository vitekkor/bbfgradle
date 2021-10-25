// Original bug: KT-32343

    class K<T>() where T : Any?, T : CharSequence {
        fun cast(x: Any?) {
            val a = x as T // No null check
            println(a)
        }
    }
    