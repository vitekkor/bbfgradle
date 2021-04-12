// Original bug: KT-20662

open class Base(val x: Any?)

object Test1 : Base(Test1)              // Error (reference to a definitely uninitialized singleton)
object Test2 : Base(Test2.toString())   // Error
object Test3 : Base(listOf(Test3))      // Error

object Test4 : Base({ Test4 })                      // OK (reference in a closure)
object Test5 : Base(fun(): Any { return Test5 })    // OK (reference in a closure)
object Test6 : Base(run { Test6 })                  // Error (reference in an inline lambda)
