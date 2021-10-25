// Original bug: KT-41402

class Foo<out T> {
    fun <K> send(f: Foo<K>, x: K) {
        println("${this.javaClass.simpleName} received '$x'")
    }
}


fun main() {
    val foo: Foo<Int> = Foo()
    foo.send(foo, "not an Int")
}
