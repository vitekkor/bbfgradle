// Original bug: KT-33764

fun Char.toDigit() = this - '0'

class MyNumber(val s: String) {
    companion object {
        fun fromString(s: String) = MyNumber(s)
    }

    fun getDigit(index: Int) = s[index].toDigit()
}
