// Original bug: KT-22738

fun foo(){} // 1

class Bar(){

    fun foo(){} // 2

    fun baz(){
        fun foo(){} // 3

        fun foobar(){
            fun foo(){} // 4
            
            foo() // Invoke 4
            this.foo() // Invoke 2
            // How to invoke foo method 1 or 3 ?
            // How to to get a method reference of 1 or 3 ?
        }
    }

}
