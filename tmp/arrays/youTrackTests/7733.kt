// Original bug: KT-26005

val x: String by null

operator fun Nothing?.getValue(instance: Any?, metadata: Any?): String = "OK"

fun box(): String = x

fun main(args: Array<String>) {
    println(box())
}
