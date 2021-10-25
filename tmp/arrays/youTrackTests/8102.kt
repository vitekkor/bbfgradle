// Original bug: KT-15887

fun suspendRef(block: suspend () -> Unit): suspend () -> Unit = block // identity function

fun main(args: Array<String>) {
    // type annotation is added to f for clarity (not needed)
    val f: suspend () -> Unit = suspendRef { println("I'm a suspending function!") }
    val g = suspendRef { 
        run { f() }    
    } 
}
