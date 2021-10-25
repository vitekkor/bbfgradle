// Original bug: KT-24564

operator fun ByteArray.set(i: Int, v: Int) {
    println("operator set invoked")
}

fun main(args: Array<String>) {
    val b = ByteArray(1)
    val v = 1 // Int type
    b[0] = v // this line resolves to operator set, which can be seen in IDEA
    println("Done")
}
