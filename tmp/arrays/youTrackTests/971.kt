// Original bug: KT-44412

fun test5() {
    var i = 0
    Outer@while (true) {
        ++i
        var j = 0
        Inner@do {
            ++j
        } while (if (j >= 3) false else break) // break@Inner
        if (i == 3) break
    }
}
