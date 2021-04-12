// Original bug: KT-42964

fun main() {
    var a = true
    if (a) { // Should be marked as "Condition is always 'true' "
        print(a)
    }
    val b = true
    if (b) { // Should be marked as "Condition is always 'true' "
        print(b)
    }
}
