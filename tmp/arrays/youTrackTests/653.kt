// Original bug: KT-42960

class A(
    val y: String,
    val y2: String,
)

operator fun A.component1(): Int = 0

fun main() {
    val a = A("", "")
    val (
        x,
    ) = a
}
