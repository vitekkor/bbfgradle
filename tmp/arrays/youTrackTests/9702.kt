// Original bug: KT-9743

fun operation(int:Int) = when (int) {
    0-> fun Int.() :Boolean { return true}
    1-> fun Int.() :Boolean { return true}
    else -> throw Exception("gah")
}
