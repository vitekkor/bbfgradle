// Original bug: KT-27586

inline class Test(val a: Any?) // it's important that underlying type is Any

class Demo {
    val a by lazy { Test(0.0) }
}

fun main(args: Array<String>) {
    println(Demo().a)
}
