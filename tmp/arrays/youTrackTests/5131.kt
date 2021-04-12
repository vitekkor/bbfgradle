// Original bug: KT-13006

fun foo(s: String) {}
fun String.bar() {}

class A {
    class B {
        fun test() {
            //             foo("s") // error: expression is inaccessible from a nested class 'B' (KT-19423)
            "s".bar() // fail with UnsupportedOperationException
        }
    }
}
