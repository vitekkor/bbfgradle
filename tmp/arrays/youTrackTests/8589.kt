// Original bug: KT-17014

fun g() {
    println("Something")
}

fun main(args: Array<String>) {

    val d = g()
    println(d)
    val u = Unit
    println(u)
    if (u==d) {
        println("Yes")
    } else {
        println("No")
    }
    
}
