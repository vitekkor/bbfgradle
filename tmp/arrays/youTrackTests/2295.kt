// Original bug: KT-28897

interface A {
    private fun foo() {
      	println("Hi!")  
    }
    
    public fun bar() {
        foo()
    }
}

class B : A {
    
    private fun foo() {
      	println("Hiiiii!")  
    }
    
}

fun main(args: Array<String>) {
    val b = B()
    
    b.bar()
}
