// Original bug: KT-33990

fun <T> foo() {} // (1)

object Scope {
    fun <T : Number> foo() {} // (2)

    fun test() {
        foo<String>() // error in OI, ok in NI (resolved to (1)) 
    }
}
