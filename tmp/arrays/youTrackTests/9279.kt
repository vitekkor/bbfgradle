// Original bug: KT-12566

fun main(args: Array<String>) {
    var log = ""

    var s: Any? = null
    for (t in arrayOf("1", "2", "3")) {
        class C() {
            val y = t

            inner class D() {
                fun foo() = "($y;$t)"
            }
        }

        if (s == null) {
            s = C()
        }

        println((s as C).D().foo())
    }
}
