// Original bug: KT-36881

class Some
fun foo(): () -> Boolean {
    val s = Some()
    if (true) {
        return { if (s is Some) true else false } // Lambda remains untouched
    } else {
        return { true }
    }
}
