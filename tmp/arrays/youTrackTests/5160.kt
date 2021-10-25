// Original bug: KT-29365

fun main() {
    val aaa = "" as Any
    when (aaa) {
        is String -> {
           println(aaa.length) // <â could show better info here
        }
    }
}
