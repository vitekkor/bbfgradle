// Original bug: KT-18764

fun indexOfMax(a: IntArray): Int? {
    if (a.size == 0) return null
    var max = a[0]
    var maxIndex = 0
    for (i in 1 .. a.size-1)
    if (a[i]>max) {
        max=a[i]
        maxIndex = i
    }
    return maxIndex
}
