// Original bug: KT-15568

fun foo() {
    val a = Array(0) { DoubleArray(0) }

    for (i in 1..1) {
        for (j in 0..2) {
            a[i][j] += a[i - 1][j]
        }
    }
}
