// Original bug: KT-41680

class A(private val someText: String) {
    
    private inner class B() : C(someText) // Redundant 'inner' modifier 
}

abstract class C(private val text: String)
