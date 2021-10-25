// Original bug: KT-4237

class A {
    val ok = "OK"
}

class B

fun box(): String {
    var o = ""
    with(A()) {
        with(B()) {
            o = ok; // in generated code wrong access to ok
        }
    }
    return o
}
