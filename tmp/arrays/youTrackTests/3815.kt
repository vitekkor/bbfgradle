// Original bug: KT-7692

class Number(val n: Int) : Function0<Int> {
    override fun invoke(): Int = n
}

fun numberAsFunction0(n: Int): Function0<Int> = Number(n)

fun main(args: Array<String>) {
    // works
    println(Number(1)()) 
    
    // crashes
    println(numberAsFunction0(2)()) 
}
