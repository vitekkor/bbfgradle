// Original bug: KT-21354

fun main(args: Array<String>) {
    var xs = intArrayOf(1, 2, 3)
    val updateXs = object : Runnable {
        override fun run() {
            xs = intArrayOf(5, 6, 7)
        }
    }
    for (x in xs) {
        println(x)
        updateXs.run()
    }
}
