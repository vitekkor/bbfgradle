// Original bug: KT-40613

class Box<T>(var value: T)

fun main() {
        val d = Box(1)
        if( d as? Box<String> != null) {
            d.value = "0123456789"
        }
        println(d.value + 2)
}
