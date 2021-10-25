// Original bug: KT-28185

inline class Z(val s: String)

fun main(args: Array<String>) {
    val a = arrayOf(Z("x"))
    println(a[0].javaClass)  // java.lang.ClassCastException: Z cannot be cast to java.lang.String
}
