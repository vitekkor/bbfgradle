// Original bug: KT-4757

class Z {
}

fun test(x: Int) {
    fun Z.local(s: Int): Int {
        return x
    }
    var s : Z? = Z()
    print(s?.local(1))
}


fun main(args: Array<String>) {
    test(1)
}
