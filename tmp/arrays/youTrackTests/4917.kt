// Original bug: KT-35178

fun main() {
    B()
}
class B : A(Foo()) {
    override val foo = Foo()
}

open class A(open val foo: Foo) {
    var x: Int = 1
        set(x) {
            field = foo.toInt() // no inspection here
        }

    init {
        x = 42
    }
}

class Foo {
    fun toInt() = 42
}
