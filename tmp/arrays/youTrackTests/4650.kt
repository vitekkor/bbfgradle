// Original bug: KT-35461

fun one(): Int = 42
fun a(f: () -> Int): Boolean {
    if (a(
            ::one
        )) {
    }
    if (a {
            42
        }) {
    }
    return false
}
