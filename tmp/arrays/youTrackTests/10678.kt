// Original bug: KT-2760

object Again {
    object More {
        fun someFunInObject() {
            println("Hello")
        }
    }

    fun test() {
        More.someFunInObject()
    }
}

fun main(args: Array<String>) {
    return Again.test()
}
