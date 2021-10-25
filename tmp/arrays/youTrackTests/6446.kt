// Original bug: KT-29088

fun main() {
    val lhs: Any? = true
    val rhs: Any? = false
    if (lhs is Boolean && rhs is Boolean) {
        println(lhs.compareTo(rhs)) // java.lang.ClassCastException: java.lang.Boolean cannot be cast to java.lang.Number
    }
}
