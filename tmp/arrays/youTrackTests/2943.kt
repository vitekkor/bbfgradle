// Original bug: KT-26768

interface A

class B : A {
    fun foo() = 10
}

fun <T> A.run(f: A.() -> T): T = f()

class Main {
    val value: Any = (B() as A).run {
        var instance = this
        if (instance is B) {
            instance.foo()
        }
    }
}

fun main(args: Array<String>) {
    val v = Main().value
    println("hello world $v")
}
