// Original bug: KT-7288

fun test(b: Boolean): String {
    val a = if (b) IntArray(5) else LongArray(5)
    if (a is IntArray) {
        val x = a.iterator()
        var i = 0
        while (x.hasNext()) {
            if (a[i] != x.next()) return "Fail $i"
            i++
        }
        return "OK"
    } else if (a is LongArray) {
        val x = a.iterator()
        var i = 0
        while (x.hasNext()) {
            if (a[i] != x.next()) return "Fail $i"
            i++
        }
        return "OK"
    }
    return "fail"
}

fun main(args: Array<String>) {
    test(true)
}

