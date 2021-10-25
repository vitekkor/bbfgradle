// Original bug: KT-19090

private fun isEmpty(value: String?): Boolean {
        val str = value ?: return true

        for (i in 0..str.length - 1)
            if (!Character.isWhitespace(str[i])) return false
        return true
}
