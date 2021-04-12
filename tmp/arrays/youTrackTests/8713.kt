// Original bug: KT-19422

fun main(args: Array<String>) {
    println("A's Run")
    for (a in 100..1){
        print(a)
    }
    println("--------------")
    println("B's Run")
    for (b in 100 downTo 1) {
        print(b)
    }
}
