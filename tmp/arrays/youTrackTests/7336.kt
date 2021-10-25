// Original bug: KT-21354

fun main(args: Array<String>) {
    println("Using for-in-array:")
    var xs = intArrayOf(1, 2, 3)
    for (x in xs) {
        println(x)
        xs = intArrayOf()
    }

    println("Using array iterator:")
    xs = intArrayOf(1, 2, 3)
    val xsi = xs.iterator()
    while (xsi.hasNext()) {
        val x = xsi.nextInt()
        println(x)
        xs = intArrayOf()
    }
}
