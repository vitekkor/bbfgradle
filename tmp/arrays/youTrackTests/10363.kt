// Original bug: KT-2163

fun printAll(vararg a : Any) {
    for (item in a) {
        println(item)
    }
}

fun main(args: Array<String>) {
    printAll(*args) // Shouldn't be an error
}
