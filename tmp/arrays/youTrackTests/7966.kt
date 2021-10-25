// Original bug: KT-19022

class C {
    
    val a: C
    val b: C get() = a
    init {
        a = b
    }
    
}
fun main(args: Array<String>) {
    println(C().a) // Prints null
}
