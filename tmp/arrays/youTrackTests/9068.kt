// Original bug: KT-12454

fun main(args: Array<String>) {
    assert(giveMeFalse())
    println("Hello")
}

fun giveMeFalse(): Boolean {
    println("With -da this is printed in Kotlin, but not in Java")
    return false
}
