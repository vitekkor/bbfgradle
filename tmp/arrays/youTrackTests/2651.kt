// Original bug: KT-40330

class Host {
    private fun bar() {
        class NamedLocal {
            fun run() {
                foo()
            }
        }
    }
    
    fun foo() {}
}
