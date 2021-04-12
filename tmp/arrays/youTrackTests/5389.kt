// Original bug: KT-27207

val instance = SomeClass("test")

class SomeClass(val prop1: String) { // "Navigate -> Call hierarchy" on `prop1` only shows constructor invocation
    val prop2 = "test"
}

class Caller1 {
    fun call() {
        val x = instance.prop1
        val y = instance.prop2
    }
}

class Caller2 {
    fun call() {
        Caller1().call()
    }
}
