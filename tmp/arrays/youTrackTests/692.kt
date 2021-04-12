// Original bug: KT-43789

fun main() {
    var a = 0
    try {
        a = 1 // UNUSED_VALUE The value '1' assigned to 'var a: Int defined in main' is never used
        a = foo()
    } catch (e: Exception) {}
    print(a) // prints 1
}

fun foo(): Int {
    5 / 0
    return 2
}
