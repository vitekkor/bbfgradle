// Original bug: KT-28029

val foo: String? = null

fun main() {
    println("<start>")
    var attached = foo?.let_ { println("do not execute this!"); 1 } ?: 3
    val capture = { attached = 2 }
    println("<end>")
}

inline fun <T, R> T.let_(block: (T) -> R): R { 
    println("<my-let>")
    return block(this)
}
