// Original bug: KT-12275

var global = ""

fun log(str: String) {
    global += str + ";"
}

fun pullLog(): String {
    val result = global
    global = ""
    return result
}

private inline fun bar(predicate: (Char) -> Boolean): Int {
    var i = -1
    val str = "abc "
    do {
        i++
        if (i == 1) continue
        log(i.toString())
    } while (predicate(str[i]) && i < 3)
    return i
}

private fun test(c: Char): Int {
    return bar {
        log(it.toString())
        it != c
    }
}

fun main(args: Array<String>) {
    println(test('a'))
    println(pullLog())

    println(test('b'))
    println(pullLog())

    println(test('c'))
    println(pullLog())

    println(test('*'))
    println(pullLog())
}
