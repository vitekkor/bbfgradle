// Original bug: KT-29402

// File 1.kt compiled with -jvm-target 1.8
open class Base {
    inline fun inlineFunBase(p: () -> Unit) {
        p()
    }
}

 inline fun packetInlineFun(p: () -> Unit) {
      p()
 }

//FILE 2.kt compiled with -jvm-target 1.6
class Derived : Base() {

    fun test() {
        inlineFunBase {}  //!no diagnostic! 
         packetInlineFun {}  //diagnostic
    }
}
