// Original bug: KT-36668

abstract class AbstractData {
    var b: String = "t"

    final override fun hashCode(): Int = b.hashCode()
    override fun toString() = "AbstractData(b=$b)"
}

data class Data0(val v: String) : AbstractData() {
    override fun toString() = "Data0(v=$v, super=${super.toString()}"
}

fun main() {
    val data0 = Data0(v = "test0")
    val data1 = Data0(v = "test0").apply {
        b = "t1"
    }
    val data2 = Data0(v = "test0")
    val data3 = Data0(v = "test1")

    println("data0.hashCode: ${data0.hashCode()}")
    println("data1.hashCode: ${data1.hashCode()}")
    println("data2.hashCode: ${data2.hashCode()}")
    println("data3.hashCode: ${data3.hashCode()}")

    println("data0.toString: $data0")
    println("data1.toString: $data1")
    println("data2.toString: $data2")
    println("data3.toString: $data3")
}
