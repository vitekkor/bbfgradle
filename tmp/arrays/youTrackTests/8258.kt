// Original bug: KT-20429

enum class Z(func: () -> Unit) {
    A(getFunc())
//    ^ Unused return value of a function with lambda expression body
}

fun getFunc(): () -> Unit = {
    println("whatever")
}
