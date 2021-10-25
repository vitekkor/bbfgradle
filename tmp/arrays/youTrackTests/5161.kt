// Original bug: KT-24242

fun main(args: Array<String>) {
    val a = args.getOrNull(0) ?: throw IllegalStateException("Call me with two arguments")
    val b = args.getOrNull(1) ?: throw IllegalStateException("Call me with two arguments")

    println(a.length)
}
