// Original bug: KT-12540

fun main(args: Array<String>) {
    for (x in arrayOf("")) {
        class Outer() {
            var p = ""

            inner class Inner() {
                fun foo() {
                    Outer()
                    Outer().p = x
                }
            }
        }

        Outer().Inner().foo()
    }
}
