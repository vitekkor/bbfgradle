// Original bug: KT-35945

class Optional<T>(val value: T? = null)
open class Person(val name: String)
class Man : Person("man")

fun doSomething(person: Optional<Optional<Person>>) { }

fun main() {
   /*
      Fails at compile time with:

       Expecting:  Optional<Optional<Person>> 
        Found:  Optional<Optional<Man>>
   */
    doSomething(Optional(Optional(Man()))) 
}
