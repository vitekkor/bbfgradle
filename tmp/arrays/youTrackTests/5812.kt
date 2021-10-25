// Original bug: KT-32402

inline class Key(val value: String)
inline class Bytes(val value: ByteArray)
inline class Ints(val value: IntArray)

fun main() {
    val k1 = Key("a")
    val k2 = Key("a")
    println(k1 == k2) // true
    
    val v1 = Bytes(byteArrayOf(1))
    val v2 = Bytes(byteArrayOf(1))
    println(v1 == v2) // false
    
    val i1 = Ints(intArrayOf(1))
    val i2 = Ints(intArrayOf(1))
    println(i1 == i2) // false
}
