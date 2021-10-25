// Original bug: KT-35687


interface Common // Removing this interface from A/B fixes problem

open class Base
class A : Base(), Common {
    fun onA() {}
}
class B : Base(), Common

fun f(bool: Boolean) {

    val x = if (bool) {
        A()
    } else {
        B()
    }

    if (bool) {
        // type of x is intersection [Base & Common]
        // cast can never succeed 
        (x as A).onA()
    }
}
