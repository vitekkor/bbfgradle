// Original bug: KT-26708

interface A {
    
    fun foo(): Unit {
        Runnable{
            println("called $x")
        }.run()
    }
    
    private companion object {
        val x = 45
    }
}

class B: A

fun main(args: Array<String>) {
    B().foo()
}
