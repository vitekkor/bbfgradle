// Original bug: KT-33524

class GenericClass<T, P>
fun <T> mock(): T = TODO()
fun function(param: GenericClass<String, String>) = Unit

class MyClass {
  val myVariable = function(mock())
}
