// Original bug: KT-31357

fun box(): String? {
    throw RuntimeException("fail")
}
fun main() {
    val a = try {
        box()
    } catch (e: RuntimeException) {
        println("CATCH e: RuntimeException")
    }!!
    println(a)
}
