// Original bug: KT-28328

fun case_1(b: Int?) {
    if (b != null || false) {
        println(b.inv()) // OK
    }
}

fun case_2(b: Int?) {
    if (b !== null) {
        println(b.inv()) // OK
    }
}

fun case_3(b: Int?) {
    if (b == null && true) else {
        println(b.inv()) // OK
    }
}

fun case_4(b: Int?) {
    if (b === null) else {
        println(b.inv()) // OK
    }
}
