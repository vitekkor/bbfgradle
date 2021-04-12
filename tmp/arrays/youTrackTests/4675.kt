// Original bug: KT-33149

typealias Bar = (String) -> Any
fun test(foo: Any) {
    if (foo is Function<*>) {
        (foo as Bar)("test")
    }
}

fun main() {
    test({ it: String -> 
        println(it)
        Any()
    })
}
