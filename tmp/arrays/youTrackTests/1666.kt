// Original bug: KT-22379

fun main(args: Array<String>) {
    var x: String? = "123"
    while (x!!.length < 42) {
        x = null
        break

    }
    println(x.length) // 'x' is unsoundly smartcasted here
}
