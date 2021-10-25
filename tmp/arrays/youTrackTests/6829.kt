// Original bug: KT-11846

open class TestClass {
    open inner class Back {
        open fun dd() { }
    }
}

class Manager: TestClass() {
    private val test = object : Back() {
        override fun dd() { }
    }
}