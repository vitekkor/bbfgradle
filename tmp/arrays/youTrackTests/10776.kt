// Original bug: KT-1844

open class Base<T>() {
    fun foo(): T = throw Exception()
}

class Subclass() : Base<String>() {

}

fun main(args: Array<String>) {
    Subclass().foo()
}
