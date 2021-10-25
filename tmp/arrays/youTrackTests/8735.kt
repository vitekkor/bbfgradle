// Original bug: KT-18450

fun play(first: Int, second: Int, fn: (Int) -> Boolean) {
    fn(first)
    fn(second)
}

fun main(args: Array<String>) {
    play(3, 4) { it > 0 }
    play(3, 4, { it > 0 })
}
