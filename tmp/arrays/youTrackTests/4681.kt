// Original bug: KT-29550

open class A(val i: Int) // Refactor > Safe delete `i`
class B : A(33) // the parameter is left as is, the code became invalid
val call = A(42) // the parameter value is just silently deleted
