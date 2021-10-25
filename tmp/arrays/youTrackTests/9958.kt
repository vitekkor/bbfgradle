// Original bug: KT-10058

import kotlin.io.println

class Outer {
    class Inner(val a: Int)
}

fun <T> output(value: Int, f: (Int) -> T) {
    println(f(value))
}

fun main(args: String) {
    output(23, Outer::Inner)
}
