// Original bug: KT-24281

package ppp
import ppp.invoke
object Bar
object Foo {
    val bar = Bar
}
fun Foo.bar() = 1
operator fun Bar.invoke() = 2

fun main(args: Array<String>) {
    println(Foo.bar()) // 2
}
