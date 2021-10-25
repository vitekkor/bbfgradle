// Original bug: KT-9569

inline fun <reified T> Any?.isValueOfType() = this is T

fun main(args:Array<String>) {
    val x : String? = null
    println(x is String?) // true -- CORRECT
    println(x.isValueOfType<String?>()) // false -- WRONG
}
