// Original bug: KT-27519

class MyClassB {

    init {
        testParam = "some string"
        showTestParam()
    }

    init {
        testParam = "new string"
    }

    val testParam: String = "after"

    constructor() {
        println("in constructor testParam = $testParam")
    }

    fun showTestParam() {
        println("in showTestParam testParam = $testParam")
    }
}

fun main(args: Array<String>) {
    MyClassB().showTestParam()
}
