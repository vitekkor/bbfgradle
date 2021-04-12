// Original bug: KT-24105

fun main() {
    val x: Int? = 42
    if (x != null && bar(x)) { // breakpoint, evaluate expression `bar(x)`
        println("OK")
    }
}

fun bar(x: Int) = true
