// Original bug: KT-45746

fun main(args: Array<String>) {
    var a = true 
    println("do smth") // set breakpoint here
    if(a) {
        println("true")
    } else {
        println("false")
    }
}
