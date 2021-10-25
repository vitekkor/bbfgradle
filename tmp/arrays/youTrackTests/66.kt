// Original bug: KT-41679

fun main() {
    var y = mutableListOf("MH", 19 as Int, true) // NI: MutableList<Comparable<*> & Serializable>, OI: MutableList<Any>
    y[0] = "value4" // OK
    println(y[0])
}
