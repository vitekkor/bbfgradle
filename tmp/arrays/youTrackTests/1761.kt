// Original bug: KT-31072

package foo

interface A
class B : A

inline fun <T : A> foo(a: Any) = (a as? T != null).toString()[0]

fun box(): String {
    val s = "" + foo<B>(Any()) + foo<B>(object : A {}) + foo<B>(B())
    if (s != "ftt") return "fail: $s"
    return "OK"
}

fun main(args: Array<String>) {
    println(box())
}
