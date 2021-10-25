// Original bug: KT-22625

package test.p1

abstract class A {
    protected val hello: String = "hello"

    protected inline fun <reified T> helloType(): () -> Unit = {
        println("$hello ${T::class.java}")
    }
}
