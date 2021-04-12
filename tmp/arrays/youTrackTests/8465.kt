// Original bug: KT-21232

fun main(args: Array<String>) {
    val result = -0.0
    println("$result") // -0.0  				
    log(result) 
}

fun log(x:Double) {
    println("$x") //0
}
