// Original bug: KT-28585

fun main() {
    var uint1 = 1u
    var uint2 = 2u
    var uint3 = 3u
    val uintSet = mutableSetOf(uint1)
    uintSet.add(uint2)
    sequenceOf(1).forEach {
        uintSet.add(uint3)
        println("uintSet contains 1? ${uintSet.contains(1u)}")
        println("uintSet contains 2? ${uintSet.contains(2u)}")
        println("uintSet contains 3? ${uintSet.contains(3u)}")
    }
}
