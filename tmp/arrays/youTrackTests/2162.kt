// Original bug: KT-42831

class Foo {
    fun foo(component: Any) {
        val bar = Bar(mutableListOf(1, 2))
        @Suppress("DEPRECATION")   // <----- removing this fixes the issue
        if (component is Baz) {
            bar.drop()             // CCE: class java.util.ArrayList cannot be cast to class kotlin.Unit
        }
    }
}
class Bar<T>(val v: T) {
    fun drop(): T? {
        return v
    }
}
class Baz
fun main() {
    Foo().foo(Baz())
}
