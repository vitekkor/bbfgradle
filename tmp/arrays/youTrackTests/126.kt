// Original bug: KT-31241

fun blah(): Long {
    val size = 0L
    size // this line does not really make sense, but there is no error/warning
    Regex // neither does this, I guess it's a reference to Regex's companion
    return size
}
