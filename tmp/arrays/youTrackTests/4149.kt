// Original bug: KT-10930

interface Base
class Derived : Base
class Sample<T>(val x: T, val f: (T) -> Unit)
// Oops! Expected type mismatch: required Sample<Base?>, found Sample<Derived?>
val sample: Sample<Base?> = Sample(Derived()) { y -> println("$y")} 
