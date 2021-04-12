// Original bug: KT-28333

fun case_1(b: Boolean?, c: Boolean?) {
    while (true) {
        c != null || break
        b!!
        // ...
    }

    b.not() // smart cast to kotlin.Boolean, NPE if b (and c) is null
}

fun main() {
    case_1(null, null) // NPE
}
