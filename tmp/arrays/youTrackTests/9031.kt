// Original bug: KT-16617

fun test() {
    f { _ ->  } // prints 'number'
}

fun f(x: (Number) -> Unit) {
    println("number")
}

@JvmName("other")
fun f(x: (Int) -> Unit) {
    println("int")
}
