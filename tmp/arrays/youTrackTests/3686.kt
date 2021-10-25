// Original bug: KT-38291

import java.util.*

var rnd = Random()
fun f(): Boolean? {
    val s = rnd.nextInt()
    if (s % 10 == 0) return null
    return true
}

fun main() {
    while(true) {
        while(f() ?: break) {
            println("in")
        }
        println("out")
    }

    println("ended")
}
