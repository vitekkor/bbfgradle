// Original bug: KT-12541

fun main(args: Array<String>) {
    var x = ""
    class Outer() {
        var p = ""

        inner class Inner() {
            fun foo() {
                Outer().p = x
            }
        }
    }

    Outer().Inner().foo()
}

