// Original bug: KT-26899


interface I {
    fun foo()
}

class Outer {
    // private matters here
    private val impl = object : I {
        override fun foo() {
            TODO("not implemented") 
        }
    }
}
