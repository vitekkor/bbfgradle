// Original bug: KT-27097

@file:JvmMultifileClass
@file:JvmName("Test")

package com.example

sealed class Foo
object Bar : Foo()

fun main(args: Array<String>) {
    println(Bar)
}
