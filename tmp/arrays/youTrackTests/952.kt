// Original bug: KT-45224

@ExperimentalStdlibApi
fun main() {
    var x: MutableList<Any>? = null
    val z = buildList {
        add("")
        x = this
    }

    val y = x

    if (y != null) {
        y.add(1) // java.lang.UnsupportedOperationException
    }

    val a = z[1]

    println(a)
}
