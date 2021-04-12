// Original bug: KT-29274

fun main() {
    fun <T, R> T.let2(block: (T) -> R): R {
        println(block)  // prints "Function1<java.lang.Integer, java.lang.Integer>"
        return block(this)
    }

    val a = 5.let2 { it }

    val f: ((String) -> Int) -> Int = ""::let2
    println(f)  // prints "function let2 (Kotlin reflection is not available)"
}
