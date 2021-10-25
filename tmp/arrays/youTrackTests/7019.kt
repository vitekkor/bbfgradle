// Original bug: KT-12208

fun printLineNumber() {
    Throwable().printStackTrace()
}

fun normalFunction() {

}

inline fun inlineFunction() {

}

fun test1() {
    inlineFunction()
    printLineNumber()
}

fun test2() {
    normalFunction()
    printLineNumber()
}

fun test3() {
    println()
    printLineNumber()
}

fun main(vararg args: String) {
    test1()
    test2()
    test3()
}
