// Original bug: KT-35858

fun main() {
    var i = 0

    val a = { if (i < 3)  '1' else '5' }
    val b = { if (i < 3) { '1' } else { '6' } }
    println(a()) // 49
    println(b()) // 1
}
