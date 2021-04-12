// Original bug: KT-32595

fun main() {
    Foo<String>().bar("Test") // Works just fine
    Foo<String>().bar<String> { false } // Is not properly resolved with NI
}

class Foo<T> {

    fun <M : T> bar(predicate: M.() -> Boolean) {}
    fun <M : T> bar(modifier: M) {}

}
