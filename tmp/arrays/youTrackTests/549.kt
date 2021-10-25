// Original bug: KT-41618

class TestClass {

    var a = 0

    fun foo() {
        qqq()
    }

    fun bar() {
        qqq()
    }

    private fun qqq() {
        a = 0 or 1
    }
}
