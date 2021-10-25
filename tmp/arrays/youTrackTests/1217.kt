// Original bug: KT-8970

class Foo(val bar: Bar = Bar.Default) {
    class Bar {
        init {
            prop.hashCode() // NPE
        }

        companion object {
            val Default = Bar()
            val prop = Any()
        }
    }
}

fun main() {
    try {
        Foo()
    } catch (e: ExceptionInInitializerError) {
        println(e.cause?.printStackTrace())
    }
}
