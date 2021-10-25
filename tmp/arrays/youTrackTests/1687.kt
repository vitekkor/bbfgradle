// Original bug: KT-22972

abstract class NumberParent: Number(){
    //A temporary fix for https://youtrack.jetbrains.com/issue/KT-22972
    abstract override fun toByte(): Byte

    abstract override fun toChar(): Char

    abstract override fun toDouble(): Double

    abstract override fun toFloat(): Float

    abstract override fun toInt(): Int

    abstract override fun toLong(): Long

    abstract override fun toShort(): Short
    
}

class MyNumber(val v: Double): NumberParent(){
    override fun toByte(): Byte = v.toByte()

    override fun toChar(): Char = v.toChar()

    override fun toDouble(): Double = v

    override fun toFloat(): Float = v.toFloat()

    override fun toInt(): Int = v.toInt()

    override fun toLong(): Long = v.toLong()

    override fun toShort(): Short = v.toShort()
}


fun main(args: Array<String>) {
    val myNumber = MyNumber(4.0)
    println(myNumber)
	println(myNumber.toDouble())
}
