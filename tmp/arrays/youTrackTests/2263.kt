// Original bug: KT-41369

class Container<T> {
    val anonFun = fun(x: T): String = x.toString() //inline this one
    val lambda = { j: T -> j.toString() } //inline this one
}

class DummyClass

fun main() {
    val container = Container<DummyClass>()

    val anonFun = container.anonFun
    val lambda = container.lambda
}
