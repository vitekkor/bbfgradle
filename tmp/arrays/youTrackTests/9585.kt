// Original bug: KT-12125

fun test(i: Int): Int {
    return i
}

fun box(): String {
    var a = 127.toByte()
    a++
    val result = test(a.toInt())
    return if (result != -128) "fail: $result" else "OK" // 128
}
