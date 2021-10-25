// Original bug: KT-17280

const val TARGET = "world"

fun printMessageNormal() {
    println("Hello " + TARGET + "!")
}

fun printMessageTemplate() {
    println("Hello $TARGET!")
}
