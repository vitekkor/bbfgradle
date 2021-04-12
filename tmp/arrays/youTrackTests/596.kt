// Original bug: KT-42903

fun main() {
    val data = Data<A>(B())
    val a = data.getSomething()
    a?.print()
}

abstract class Base
class A : Base() {
    fun print() = println("123")
}
class B : Base()

class Data<T : Base>(val b: Base) {
    fun getSomething(): T? = b as T
}
