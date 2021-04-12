// Original bug: KT-43742

fun find(p: Int): Int {
    var arr = arrayOf(p)

    @Suppress("NAME_SHADOWING")
    var p = p

    while (p != arr[p]) {
        arr[p] = arr[arr[p]]
        p = arr[p]
    }

    return p
}
