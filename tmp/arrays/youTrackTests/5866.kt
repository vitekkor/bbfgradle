// Original bug: KT-28715

open class Base {
      fun foo() {
      }
    
    fun toCompanion() {
        foo()
    }
}


class Child: Base() {
    
    fun test() {
        toCompanion()
    }
}
