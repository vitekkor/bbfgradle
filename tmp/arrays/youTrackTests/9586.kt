// Original bug: KT-12125

fun test(i: Int): Int {
    return i
}

fun box(): String {
    var a = 127.toByte()
    val result = test(a.inc().toInt())
    return if (result != -128) "fail: $result" else "OK" //128
}
