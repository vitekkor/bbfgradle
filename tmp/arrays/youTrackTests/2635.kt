// Original bug: KT-28860

class A {
    var foo = E.A

    @Deprecated("", ReplaceWith("foo = E.B"))
    fun setLoading() {
        foo = E.B
    }
    
    fun test() {
         setLoading()
    }
}

enum class E { A, B }
