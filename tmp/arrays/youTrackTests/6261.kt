// Original bug: KT-30337

    fun foo(s: String): Int {
        val capitalized = s.capitalize()
        when (capitalized) {
            "A" -> return 1
            "B" -> return 2
        }
        return 3
    }
