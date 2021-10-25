// Original bug: KT-12921

package org.devlee.myapplication

class Foo {
    fun run() = produce { object { val text = text() } }.text // Class Not Found Exception
    fun runWithConstant() = produce { object { val text = "text" } }.text // Worked
    fun runNotInline() = produceNotInline { object { val text = text() } }.text // Worked
    fun runWithoutProduce() = { object { val text = text() } }().text // Worked


    fun text() = "test"
}

fun main(args: Array<String>) {
    Foo().runWithoutProduce() // Done
    Foo().runNotInline() // Done
    Foo().runWithConstant() // Done
    Foo().run() // Class Not Found Exception
}

inline fun <R> produce(func: (() -> R)) : R = func()
fun <R> produceNotInline(func: (() -> R)) : R = func()
