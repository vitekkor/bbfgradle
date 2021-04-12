// Original bug: KT-26179

class Test {
    fun foo(v: Boolean): Boolean?{return null} 

    fun test() {
        foo(false || !true) ?:return //no warning reported here
        print(2)
    }    
}
