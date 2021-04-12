// Original bug: KT-26795

class Demo {

    companion object {
        private const val NAME = "name"
        const val VALUE = "value"
    }


    fun sayHi() {
        println(NAME)
        println(VALUE) // Set breakpoint in this method
    }
}


fun main(args: Array<String>) {
    Demo().sayHi()
}

