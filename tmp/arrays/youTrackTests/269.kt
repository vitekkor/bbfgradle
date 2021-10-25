// Original bug: KT-31994

class Test {
    inner class Inner {
        fun outerRef() = this@Test
    }
}

fun testBug() {
    val test: Test? = null
    test?.Inner()?.outerRef()
}
