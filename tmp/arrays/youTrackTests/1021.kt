// Original bug: KT-17728

fun main(args: Array<String>) {
    var i = 0
    Outer@while (true) {
        ++i
        println("Outer: $i")
        var j = 0
        Inner@do {
            ++j
            println("Inner: $j")
        } while (if (j >= 3) false else break) // break@Inner
        if (i == 3) break
    }
}
