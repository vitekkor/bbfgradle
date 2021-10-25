// Original bug: KT-25112

class A {
    companion object {
// public final static synthetic access$getFoo$cp()I
        private val foo = 1
        
        fun bar() = foo
    }
}
