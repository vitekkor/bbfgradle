// Original bug: KT-25474

class Bar

class Foo {
    val bar = Bar()
}

inline operator fun Bar.invoke(f: () -> String) { f() }

fun box(): String {
    Foo().bar { return "OK" }
    return "fail"
}

fun main(args: Array<String>) {
    println(box())
}
