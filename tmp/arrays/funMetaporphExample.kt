// TARGET_BACKEND: JVM

fun fooBar(a: Int, b: Int, c: Int): Int {
    val v1 = maxOf(a, b, c)
    val v2 = minOf(a, b, c)
    val v3 = a + b + c - v1 - v2
    var v4 = (v2 + v3 - v1) / 2
    //INSERT_CODE_HERE
    if ((a >= b + c) || (b >= a + c) || (c >= a + b)) {
        return -1
    }
    return when {
        v4 == 0 -> 1
        v4 < 0 -> 2
        else -> 0
    }
}

class MyClass()

fun another() {
    fooBar(5, 6, 7)
}

fun multiply(x: Int, y: Int) = x * y

fun boxBox() = box()

fun box(): Int {
    val a_ = 7
    val b_ = 8
    var result = 0
    for (i in 1..100) {
        result += fooBar(a_, b_, i)
    }
    return result
}