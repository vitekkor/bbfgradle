// Original bug: KT-19943

class TestIntValue {
    fun testByte(xs: List<Byte>): Byte {
        return xs[0].toByte() // redundant 'toByte'
    }

    fun testInt(xs: List<Int>): Int {
        return xs[0].toInt() // redundant 'toInt'
    }
}
