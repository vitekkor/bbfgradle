// Original bug: KT-37171

fun String.test() { // called
    println("In test")
}

fun String.test(value: Boolean = true) { //shadowed
    println("In boolean test")
}

fun main() {
    "hello".test()
}
