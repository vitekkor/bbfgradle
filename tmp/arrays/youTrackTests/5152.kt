// Original bug: KT-33982

tailrec fun tailrecFun(number: Int): Int = if (number < 10) {
    number
} else {
    tailrecFun(number - 1) // breakpoint here
}

fun main() {
    tailrecFun(15)
}
