// Original bug: KT-45224

fun <T: Int> run(x: MutableList<T>) {
    val x: Int = x[0] // CCE: String cannot be cast to Number
}

@ExperimentalStdlibApi
fun main() {
    var x: MutableList<Any>? = null
    val z = buildList {
        add("")
        run(this)
    }
}
