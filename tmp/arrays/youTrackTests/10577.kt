// Original bug: KT-3800

fun main(args: Array<String>) {
    for (i in listOf(1, 2, 3)) {
        if (i > 2) continue
        println( // Breakpoint does not work on this line
                i // but works on this line
        )
    }
}
