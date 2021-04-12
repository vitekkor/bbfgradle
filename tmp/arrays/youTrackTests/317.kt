// Original bug: KT-27102

inline val IntArray.foo inline get() = indices

fun main(args: Array<String>) {
    val arr = IntArray(5)

    for (i in arr.foo) {
        println(i)
    }
}
