// Original bug: KT-40381

class Outer {
    companion object {
        fun bar(x: String) {
            class Local {
                fun foo2() {
                    foo(x)
                }
            }
        }

        fun foo(x: String) {}
    }
}
