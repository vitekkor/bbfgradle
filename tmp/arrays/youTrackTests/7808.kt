// Original bug: KT-22495

fun main(args: Array<String>) {
    val x = -0.0
    val y =  0.0
    
    val r = 0.0 .. 0.0
    
    println(x in 0.0 .. 0.0)
    println(x in r)
    println(r.contains(x))
    println((0.0 .. 0.0).contains(x))
    
    // everything above prints 'true'
}
