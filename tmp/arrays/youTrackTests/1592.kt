// Original bug: KT-23447

class A : Number(){
    override fun toByte(): Byte = 0
    override fun toChar(): Char = 42.toChar()
    override fun toDouble(): Double = 0.0
    override fun toFloat(): Float = 0f
    override fun toInt(): Int = 64
    override fun toLong(): Long = 0
    override fun toShort(): Short = 0
}

fun main() {
    val code: Number = A()
    val c = code.toChar()
    println(c)
}
