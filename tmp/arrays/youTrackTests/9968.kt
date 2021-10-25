// Original bug: KT-7837

class A
class B

fun test(a: Any) {
    val q: String

    when (a) {
        is A -> {
            q = "1"
        }
        is B -> {
            q = "2"
        }
        else -> null!!
    }

    listOf(q)
}

fun main(args: Array<String>) {
    test(A())
}
