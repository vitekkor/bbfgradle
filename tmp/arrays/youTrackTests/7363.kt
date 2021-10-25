// Original bug: KT-27693

package test

inline class S(val string: String)

fun testMangled(s: S) {
    class Local

    val localKClass = Local::class
    val localJClass = localKClass.java
    println(localKClass.simpleName)
    println(localJClass.simpleName)
}

fun testNonMangled() {
    class Local

    val localKClass = Local::class
    val localJClass = localKClass.java
    println(localKClass.simpleName)
    println(localJClass.simpleName)
}

fun main(args: Array<String>) {
    testMangled(S(""))
    testNonMangled()
}
