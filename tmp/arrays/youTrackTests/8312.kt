// Original bug: KT-18477

fun justTodo() {
    TODO() // yellow-green, as an ordinary TODO
}

fun todoToVal() {
    val td = TODO() // yellow-green too
}

fun todoToFun() {
    listOf(TODO()) // white, as an ordinary function
}
