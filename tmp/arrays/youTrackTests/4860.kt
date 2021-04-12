// Original bug: KT-30507

operator fun MutableList<Int>.get(i1: Int, i2: Int) {}
operator fun MutableList<Int>.set(i1: Int, i2: Int, el: Int) = 10

fun main() {
    var x: MutableList<Int>? = mutableListOf(1)
    x!!
    val y = x[if (true) {x=null;0} else 0, x[0]] // x in the second argument position is unsound smartcast, NPE
}
