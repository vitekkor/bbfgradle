// Original bug: KT-28994

inline class InlineClass(val x: Int)
class PlainClass(val x: Int)
fun main() {
    val ic = InlineClass(1)
    val pc = PlainClass(2)
    val ui = 3u
    println("bye")
}
