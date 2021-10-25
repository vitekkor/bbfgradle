// Original bug: KT-34372

enum class B2 {
    A {
        override fun foo(s: () -> String) {
           //smth
        }
    };

   //SHOULD BE ERROR REPORTED
    inline open  fun foo(s: () -> String) { 
        s()
    }
}
