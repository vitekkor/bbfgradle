// Original bug: KT-21354

fun main(args: Array<String>) {
    var xs = intArrayOf(1, 2, 3)
    fun updateXs() { 
        xs = intArrayOf(5, 6, 7) 
    }
    for (x in xs) {
        println(x)
        updateXs()
    }
}
