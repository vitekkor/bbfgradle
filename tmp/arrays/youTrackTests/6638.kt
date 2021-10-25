// Original bug: KT-28329

fun case_1(b: Int?) {
    if (false || b != null) {
        println(b.inv()) // OK
    }
}

fun case_2(b: Int?) {
    if (b != null || false || false || false) {
        println(b.inv()) // OK
    }
}

fun case_3(b: Int?) {
    if (true && b == null) else {
        println(b.inv()) // OK
    }
}

fun case_4(b: Int?) {
    if (b == null && true && true) else {
        println(b.inv()) // OK
    }
}

fun case_5(b: Int?) {
    if (false || b != null || false || false || false) {
        println(b.inv()) // OK
    }
}
