// Original bug: KT-28265

fun case_2(x: Number?) {
    val y: Int? = null

    if (y == x) {
        println(x) // x is smart cast to {Int?}
    }
    if (x == y) {
        println(x) // x is {Number?}
    }
}
