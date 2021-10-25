// Original bug: KT-21757

fun main(args: Array<String>) {
    val n = 3
    fun localFunInMain1(){
        println(n) // Frames: anonymous(), but expected as `main$localFunInMain1()`
    }
    fun localFunInMain2(){
        println() // Frames: main$localFunInMain2()
    }
    localFunInMain1()
    localFunInMain2()
}