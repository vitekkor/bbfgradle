// Original bug: KT-16727

package test

interface I {
    fun test() = object : ArrayList<String>() {}
}

class C : I

fun main() {
    val testAnonClass = C().test().javaClass
    println("<${testAnonClass.enclosingClass.canonicalName}>")
    println("<${testAnonClass.name}>")
    println("<${testAnonClass.simpleName}>")
}
