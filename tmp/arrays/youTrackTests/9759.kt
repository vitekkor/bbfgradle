// Original bug: KT-5509

class A {
    fun Main() {
        val t : B<Int>.C<String>? = null 
        t!!.use(1,"dsf") // bogus error about converting 1 to T
    }
}

class B<T> {
    public inner class C<S> {
        fun use(t : T, s : S) {
            
        }
    }
}
