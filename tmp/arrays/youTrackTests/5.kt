// Original bug: KT-45508

interface Foo {
    fun check(): String = "OK"
}
abstract class Base {
    abstract fun check(): String
}
abstract class Derived : Base(), Foo

 //missed diagnostic here
class Derived2 : Derived()

fun box(): String {
    return Derived2().check() //program could be successfully executed with `OK` output
}
