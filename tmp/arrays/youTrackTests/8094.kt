// Original bug: KT-11233

class A {

    fun test() {
        inlineFun {
            {
                val z = this@A;
                {
                    it: Any? ->
                } (null);
            }()
        }
    }

}

inline fun inlineFun(s: () -> Unit) {
    s()
}
