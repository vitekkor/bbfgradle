// Original bug: KT-18857

package test

val x = 42

fun main(args: Array<String>) {
    when (x) {
        42 -> {
            println("42")
            if (x < 0) { // (*) breakpoint
                println("Hmm?") // (**)
            }
        }
        1 -> println("1")
        else -> println("No")
    }
}
