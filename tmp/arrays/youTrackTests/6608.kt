// Original bug: KT-28262

class A {}
fun case_1(y: A?) {
    val x: A = A()

    if (x === y == true) {
        println(y) // y â {A?}, not {A}
    }
    if (x === y != false) {
        println(y) // y â {A?}, not {A}
    }
}
