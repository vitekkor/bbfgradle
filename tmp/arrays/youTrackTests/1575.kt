// Original bug: KT-41076

interface MyLogic {
    fun String.foo(s: String = ""): String
}

class MyLogicImpl : MyLogic {
    override fun String.foo(s: String): String = this + s
}

class MyLogicTest {
    private val logic = MyLogicImpl()
}
