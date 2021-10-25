// Original bug: KT-42579

fun main() {
    var obj: Any = "Hello"
    if (obj is String) {
        obj = "World"
        println(obj.length)
        obj = 5
        println(obj.countOneBits())
    }
}
