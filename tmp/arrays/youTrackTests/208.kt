// Original bug: KT-20662

open class Base(val x: Any?)

interface IFoo

object Test10 : Base(object : Base(Test10) {})                // Error (reference in an anonymous object constructor)

object Test11 : Base(object : IFoo by Test11 {}), IFoo      // Error (reference in an anonymous object constructor)

object Test12 : Base(object { val test = Test12 })          // Error (reference in an anonymous object constructor)

object Test13 : Base(object { val test by Test13 }) {       // Error (reference in an anonymous object constructor)
    operator fun getValue(thisRef: Any, prop: Any) = 42
}

object Test14 : Base(object { fun test() = Test14 })        // OK (reference in an anonymous object method)
