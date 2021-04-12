// Original bug: KT-42136

open class B
class C : B()

/*1*/ val list = listOf<Any>().map { C() as B }.toMutableList()
                                     // ^^^ [USELESS_CAST] No cast needed
/*2*/ fun f() { list.add(B()) }
