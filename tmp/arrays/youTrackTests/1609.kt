// Original bug: KT-30319

class Outer {
    private interface I1 {
        val a get() = 0
        fun foo()
    }

    private interface I2 : I1 {
        override val a get() = 1
        override fun foo()
    }
}
