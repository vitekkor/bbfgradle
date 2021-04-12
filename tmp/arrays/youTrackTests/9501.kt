// Original bug: KT-10798

package com.example

fun main(args: Array<String>) {
    C().fooBar()
}

interface A {
    fun foo() {}
}

interface B {
    fun bar() {}
}

class C : A, B

fun <T> T.fooBar() where T : A, T : B {
    foo()
    bar()
}
