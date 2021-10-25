// Original bug: KT-30312

fun main() {
    val x = 2
    doSmth(2) {it % 2 == 0}
    val y = 3
}

fun doSmth(x: Int, whatToDo: (Int) -> Boolean): Boolean {
    var innerParam = x
    while (innerParam < 10) {
        innerParam += 1
    }
    return whatToDo(innerParam)
}
