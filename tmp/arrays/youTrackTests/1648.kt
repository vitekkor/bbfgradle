// Original bug: KT-27498

val map = mapOf<String, Any?>("x" to null)

val x: String by map

fun main(args: Array<String>) {
    println(x) // silently prints null, but should be NPE
}
