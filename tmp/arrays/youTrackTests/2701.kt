// Original bug: KT-30803

package test

class L

operator fun L?.invoke() {
    println(this)
}

class X

val X?.x: L?
    get() {
        println("get")
        return if (this == null) null else L()
    }

fun test1(nx: X?) = nx?.x()

fun main() {
    test1(X())
}
