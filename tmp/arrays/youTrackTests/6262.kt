// Original bug: KT-30337

    fun foo(s: String): Int {
        when (val capitalized = s.capitalize()) {
            "A" -> return 1
            "B" -> return 2
        }
        return 3
    }
