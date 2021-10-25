// Original bug: KT-22411

fun main(args: Array<String>) {
    val a: Array<Int> = arrayOf(1,2,3,4)
    val b = (a as Array<Any>)
    b[2] = "AAA" // Exception in thread "main" java.lang.ArrayStoreException: java.lang.String
}
