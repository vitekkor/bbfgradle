// Original bug: KT-11573

sealed class Base

class Derived : Base()

object Instance : Base()

fun foo(base: Base) {
    when (base) {
        is Derived -> println("Derived")
        Instance -> println("Instance")
    }
}
