// Original bug: KT-29878

fun case_1(x: Any?) {
    if (x is Int == true) {
        x.inv() // OK
    }
}

fun case_2(x: Any?) {
    if (x != null != false) {
        x.equals(10) // OK
    }
}
