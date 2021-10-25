// Original bug: KT-36394

import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var el: String?
    var o_el: String? = ""
    el = br.readLine()
    val count = el.toInt()
    for (i in 1..count) {
        el = br.readLine()
        if (el != o_el) {
            println(el)
        }
        o_el = el
    }
}
