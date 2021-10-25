// Original bug: KT-41103

fun main() {
    var a = false
    run {
        a = true // no warning
    }
    var b = false
    if (true) {
        b = true // The value 'true' assigned to 'var b: Boolean defined in main.main' is never used
    }
}
