// Original bug: KT-29141

val seq = sequence {
    while (true) {
        val line = readLine()
        if (line != null) {
            yield(line!!) // warning: redundant non-null assertion
        } else {
            break
        }
    }
}
