// Original bug: KT-25514

fun foo(x: Int, vararg y: String) {}

fun useArray(f: (Int, Array<String>) -> Unit) {}

fun test() {
    useArray(::foo) // OK without new inference, should be OK with NI as well
}
