// Original bug: KT-37091

enum class A() {
    A1, A2;
}

class B()
class C(val b : B)
// TESTCASE NUMBER: 1
fun case1() {
    val flag = A.A1
    val l0: B = when (flag!!) {
        A.A1 -> B()
        A.A2 -> B()
    }
    val x1 = C(l0) //ok (l0 is B)
}


// TESTCASE NUMBER: 3
fun case3() {
    val flag = A.A1

    val l1 = when (flag!!) {
        A.A1 -> B()
        A.A2 -> B()
    }
    val x1 = C(l1) //INAPPLICABLE_CANDIDATE (l1 is Unit)
}

// TESTCASE NUMBER: 4
fun case4() {
    val flag = A.A1
    val l1 = when (flag) { //there is no null-assertion! , no explicit type
        A.A1 -> B()
        A.A2 -> B()
    }
    val x1 = C(l1) //ok (l1 is B)
}
