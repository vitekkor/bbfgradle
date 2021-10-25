// Original bug: KT-29075

package sample

fun main(args: Array<String>) {
    val temp = SomeClass(5.toUInt())
    println(temp.v)
}

interface SomeIface<T> {
    val v: T
}

class SomeClass(override val v: UInt) : SomeIface<UInt>
