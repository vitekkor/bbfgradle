// Original bug: KT-2151

fun foo(): Int {
    return if (true) {
        var x = 1
        fun foo() { x += 4 }
        x
    } else 12
}

fun main(args: Array<String>) {
    foo()
}
