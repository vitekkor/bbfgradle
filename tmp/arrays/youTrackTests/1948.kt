// Original bug: KT-35716

interface Test {
    companion object {
        @JvmStatic
        @JvmOverloads
        fun sayHello(greeting: String = "Hello,") {
            println("$greeting world!")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            sayHello()
        }
    }
}
