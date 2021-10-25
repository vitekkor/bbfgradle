// Original bug: KT-16844

val Byte.hex get() = Integer.toHexString(toInt() and 0xFF or 0x100).substring(1, 3).toUpperCase()
val ByteArray.hex get() = map(Byte::hex).joinToString(" ")
val String.hex get() = toByteArray(Charsets.UTF_8).hex


fun main(args: Array<String>) {
    println("hello".hex)
}