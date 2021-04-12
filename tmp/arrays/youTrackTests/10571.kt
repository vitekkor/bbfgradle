// Original bug: KT-3286

fun test(x: Int): String {
    when(x) {
        0 -> return "zero"
        1 -> return "one"
        2 -> return "two"
        else -> blowUpHorribly()
    }
}

fun blowUpHorribly(): Nothing = throw RuntimeException("Blow up!")

fun main(args: Array<String>) {
    println(test(1))
}
