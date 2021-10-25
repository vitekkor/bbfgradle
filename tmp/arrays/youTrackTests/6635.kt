// Original bug: KT-29577

fun main() {
    println(StrReader("a").peek())
}

inline fun String.getOrElse(index: Int, default: () -> Char): Char = if (index >= 0 && index < this.length) this[index] else default()

class StrReader(val str: String, var pos: Int = 0) {
        // NO BOXING
	fun peek2(): Char = if (pos >= 0 && pos < str.length) str[pos] else '\u0000'
	
	// BOXING
	fun peek(): Char = str.getOrElse(pos) { '\u0000' }
}
