// Original bug: KT-18126

    fun CharSequence.splitter(start: Int): Pair<Int, Int>? {
        var depth = 0
        for (index in start until length) {
            when (get(index)) {
                ',' -> if (depth == 0) return Pair(index, 1)
                '(', '[', '{' -> depth++
                ')', ']', '}' -> depth--
            }
        }
        return null
    }
