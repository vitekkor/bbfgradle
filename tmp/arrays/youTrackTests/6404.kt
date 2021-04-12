// Original bug: KT-29753

fun position(arg: Double = 0.0 ){ println("B") }
fun position(arg: Int = 0, arg2: Int = 1) { println("A") }

fun main(vararg args: String){
    position()
}
