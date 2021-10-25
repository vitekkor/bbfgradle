// Original bug: KT-36668

abstract class AbstractData {
    var b: String = "t"

    override fun hashCode(): Int = b.hashCode()
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

    println("data0.hashCode: ${data0.hashCode()}")
    println("data1.hashCode: ${data1.hashCode()}")

    println("data0.toString: $data0")
    println("data1.toString: $data1")
}
