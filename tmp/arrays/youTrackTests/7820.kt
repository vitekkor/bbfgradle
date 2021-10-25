// Original bug: KT-7922

fun main(args: Array<String>) {
    println(Boolean::class.java == java.lang.Boolean.TYPE) // true
    println(Int::class.java == java.lang.Integer.TYPE) // true
}
