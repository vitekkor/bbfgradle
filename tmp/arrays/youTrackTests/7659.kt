// Original bug: KT-25810

package main

import kotlin.reflect.KProperty

object Foo

object Bar

object Baz

operator fun Foo.provideDelegate(receiver: Any?, property: KProperty<*>) = this

// Error does NOT occur when the following operator is commented out
operator fun Bar.provideDelegate(receiver: Any?, property: KProperty<*>) = this

operator fun Foo.getValue(nothing: Any?, property: KProperty<*>): Any = TODO()

operator fun Bar.getValue(nothing: Any?, property: KProperty<*>): Any = TODO()

operator fun Baz.getValue(nothing: Any?, property: KProperty<*>): Any = TODO()

fun main(args: Array<String>) {
    val foo by Foo
    val bar by Baz
//    e: Main.kt: (24, 16): Overload resolution ambiguity on method 'provideDelegate(Nothing?, KProperty<*>)':
//    public operator fun Bar.provideDelegate(receiver: Any?, property: KProperty<*>): Bar defined in main
//    public operator fun Foo.provideDelegate(receiver: Any?, property: KProperty<*>): Foo defined in main
    println("Hello, world!")
}
