// Original bug: KT-7630

class MyInt(var value: Int = 0) {
    operator fun plus(other: Int): MyInt? = null
}

fun main(args: Array<String>) {
    var x: MyInt?

    x = MyInt(42)

    while (true) {
        x += 42
        x.toString() // Unsound smartcast
    }
}
