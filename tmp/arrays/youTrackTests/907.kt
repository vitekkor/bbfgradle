// Original bug: KT-44722

inline class Value(val value: String)

fun main() {
    Value("value").let(Foo::print) // ClassCastException: class Value cannot be cast to class java.lang.String
}

object Foo {

    fun print(value: Value) {
        println(value)
    }
}
