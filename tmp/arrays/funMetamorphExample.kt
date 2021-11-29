// TARGET_BACKEND: JVM

fun fooBar(a: Int, b: Int, c: Int): Int {
    var v1 = maxOf(a, b, c)
    val v2 = minOf(a, b, c)
    val v3 = a + b + c - v1 - v2
    val string = "some string"
    val empty = ""
    val sssss = "//INSERT_CODE_HERE"
    val doub = 2.0
    val float = 1.0f
    val bool = true
    val ui: UInt = 1u
    val ch = 'f'
    var v4 = (v2 + v3 - v1) / 2
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