// Original bug: KT-44185

class CC {}
val a = CC()
val arr: Array<CC> = Array(2) {
    if (it == 0) {
        a
    } else {
        b
    }
}
val b = CC()

fun main() {
    println(arr.get(0)!!)
    println(arr.get(1)!!)
}
