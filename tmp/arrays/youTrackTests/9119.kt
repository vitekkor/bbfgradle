// Original bug: KT-16275

fun printNumberSign(num: Int) {
    if (num > 0) {
        "positive"
    } else if (num < 0) {
        "negative"
    } else {
        "zero"
    }.let { print(it) }
}

fun main(args: Array<String>) {
    printNumberSign(1)
}
