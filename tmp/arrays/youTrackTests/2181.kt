// Original bug: KT-39062

fun bar(x: String, y: String) { print("$x $y\n") }

fun workingAlternative(a: List<String?>?, b: List<String>) {
    if (a != null) {
        if (a.size != b.size) {
            return
        }
        for (i in a.indices) {
            val x = a[i]
            val y = b[i]
            if (x != null) {
                bar(x, y)
            }
        }
    }
}
