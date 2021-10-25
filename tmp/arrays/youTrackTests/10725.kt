// Original bug: KT-1959

open class A<T> {
    open fun f(args : Array<T>) {}
}

class B(): A<String>() {
    override fun f(args : Array<String>) {}
}

fun main(args: Array<String>) {
    B()
}
