// Original bug: KT-3152

public class Test {
    val content = 1
    inner class A {
        val v = object {
            fun f() = content
        }
    }
}

fun main(args: Array<String>) {
    Test().A()
}
