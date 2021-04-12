// Original bug: KT-33310

open class ConstructedComp(val value: Int) {
    companion object {
        val x: Int = 42
    }
}

class ContextClass {
    val x: String = ""
    
    class Nested : ConstructedComp(x) // OK, because 'x' is resolved to ConstructedComp.Companion.x, not to ContextClass.x
}
