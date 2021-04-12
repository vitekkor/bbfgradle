// Original bug: KT-40746

fun main () {
    val list = listOf("Hello world!").toTypedArray()
    create(list)
}

fun create(any: Any) {
    println("Hello world 1")
}

fun create(array: Array<Any>) {
    println("Hello world 2")
}
