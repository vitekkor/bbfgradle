// Original bug: KT-30507

operator fun Int.invoke(y: Int, z: Int) {}

fun main() {
    var x: Int? = 10
    x!!
    x(if (true) {x=null;0} else 0, x) // x in the second argument position is unsound smartcast, NPE
    x.inv() // unsound smartcast, NPE
}
