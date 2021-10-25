// Original bug: KT-9825

fun test5() {
    var a: Int
    try {
        a = 3 // the value assigned to ... is never used: True
    }
    finally {
        a = 5 // the value assigned to ... is never used: Fake
    }
    a.hashCode()
}
