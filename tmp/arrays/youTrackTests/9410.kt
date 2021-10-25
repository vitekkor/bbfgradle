// Original bug: KT-13241

fun main(args: Array<String>) {
    foo(intArrayOf(1))
}

fun foo(x: Any) {
    if (x is IntArray) {
        for (i in x.indices) {
            print(i)
        }
    }
}
