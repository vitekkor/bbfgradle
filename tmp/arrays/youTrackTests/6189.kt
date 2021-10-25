// Original bug: KT-30903

fun case_2(y: Boolean?) {
    val x = when (y) {
        true -> 1
        false -> when (y) { // OK
            true -> 1
            false -> 2
        }
        null -> 3
    }
}
