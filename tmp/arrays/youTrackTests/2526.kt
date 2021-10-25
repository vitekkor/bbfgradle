// Original bug: KT-22723

fun test1(x: Any, y: Double) = x is Double && x == y

fun test2(x: Any, y: Double): Boolean {
    if (x !is Double) return false
    val tmp: Double = x
    return tmp == y
}

fun test3(x: Comparable<Double>, y: Double) = x is Double && x < y

fun test4(x: Comparable<Double>, y: Double): Boolean {
    if (x !is Double) return false
    val tmp: Double = x
    return tmp < y
}

fun test5(x: Any, y: Any) = x is Double && y is Int && x < y

fun test6(x: Double, y: Int) = x < y

fun test7(x: Any) =
        when (x) {
            !is Double -> "!Double"
            0.0 -> "0.0" // (*)
            else -> "other"
        }

fun test8(x: Any) =
        if (x !is Double) "!Double"
        else if (x == 0.0) "0.0"
        else "other"

fun main(args: Array<String>) {
    println("test1(0.0, -0.0):      ${test1(0.0, -0.0)}")
    println("test2(0.0, -0.0):      ${test2(0.0, -0.0)}")
    println("test3(-0.0, 0.0):      ${test3(-0.0, 0.0)}")
    println("test4(-0.0, 0.0):      ${test4(-0.0, 0.0)}")
    println("test5(-0.0, 0):        ${test5(-0.0, 0)}")
    println("test6(-0.0, 0):        ${test6(-0.0, 0)}")
    println("test7(-0.0):           ${test7(-0.0)}")
    println("test8(-0.0):           ${test8(-0.0)}")
}
