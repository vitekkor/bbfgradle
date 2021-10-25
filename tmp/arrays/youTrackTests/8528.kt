// Original bug: KT-19439

class A : B({ "${C.c}" }) // introduce parameter C.c fails

class C() {
    companion object {
        val c = 23
    }
}

open class B(param: () -> String)
