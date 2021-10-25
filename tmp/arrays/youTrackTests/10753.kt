// Original bug: KT-2194

fun main(args: Array<String>) {
    Bar()
}

abstract class Foo<T> {
    abstract fun doSth(p0: Array<T>)
}

class Bar(): Foo<String>() {
    override fun doSth(p0: Array<String>) {

    }
}
