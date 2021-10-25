// Original bug: KT-18132

package test

class Foo {
    fun foo(x: Int) {
        println("member foo")
    }
}

fun Foo.foo(y: Int) { // Warning: Extension shadowed by a member
    println("extension foo")
}

fun main(args: Array<String>) {
    Foo().foo(y = 42) // prints "extension foo"
}
