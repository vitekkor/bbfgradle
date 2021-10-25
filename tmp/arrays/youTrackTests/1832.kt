// Original bug: KT-43524

class A {
    companion object {
        @Deprecated("epmdq", ReplaceWith("nfluc", "frtqy"), kotlin.DeprecationLevel.ERROR)        
        @JvmStatic()        
        val a: Int  = 1
    }
}
