// Original bug: KT-36394

fun main(args: Array<String>) {
    var el: String?
    var o_el: String? = ""
    el = readLine()!!
    val count = el.toInt()
    for (i in 1..count) {
        el = readLine()!!
        if (el != o_el) {
            println(el)
        }
        o_el = el
    }
}
