// Original bug: KT-39062

fun bar(x: String, y: String) { print("$x $y\n") }

fun minimalRepro(a: List<String?>?, b: List<String>) {
    if (a != null) {
        if (a.size != b.size) {
            return
        }
        for ((x, y) in (a zip b)) {
            if (x != null) {
                bar(x, y)
            }
        }
    }
}
