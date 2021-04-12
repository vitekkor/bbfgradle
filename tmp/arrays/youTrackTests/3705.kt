// Original bug: KT-37374

fun takeString(s: String) {}
fun takeInt(s: Int) {}

infix fun Any.foo(other: Any): Int = 1     // (1)
fun String.foo(other: String): String = "" // (2)

fun test_1() {
    val res = "" foo "" // should resolve to (1)
    takeInt(res)
}

fun test_2() {
    val res = "".foo("") // should resolve to (2)
    takeString(res)
}
