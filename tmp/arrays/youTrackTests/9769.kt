// Original bug: KT-7213

class A {
    constructor(s: Int) {}
    constructor(s: String) {}
    val property = {}
}

fun main(args: Array<String>) {
    println(A(4).property.javaClass.getEnclosingConstructor()) // must be null, but now: "public A(java.lang.String)"
}
