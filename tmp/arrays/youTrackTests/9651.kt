// Original bug: KT-7265

fun operation(int:Int) = when (int) {
    0-> fun Int.() :Boolean { return true}
    1-> fun Int.() :Boolean { return true}
    else -> throw Exception("gah")
}
