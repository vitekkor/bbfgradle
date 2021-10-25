// Original bug: KT-27113

class CharacterLiteral(private val prefix: NamelessString, private val s: NamelessString) {
    override fun toString(): String = "$prefix'$s'"
}

inline class NamelessString(val b: ByteArray) {
    override fun toString(): String = String(b)
}

fun main(args: Array<String>) {
    println(CharacterLiteral(NamelessString("u".toByteArray()), NamelessString("test".toByteArray())))
}
