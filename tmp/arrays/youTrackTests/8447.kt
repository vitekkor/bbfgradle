// Original bug: KT-21372

class StringDigest(val nChars: Int) {
    fun createDigest(string: String) =
        string.take(nChars) + if (string.length > nChars) "..." else ""
}

fun StringDigest.group(strings: Iterable<String>) =
    strings.groupBy(::createDigest)
