// Original bug: KT-25113

class A {
    private companion object {
        const val x = 1
        @JvmField
        val q = Any()
    }
    
    fun bar() {
        println(x.toString() + q)
    }
}
