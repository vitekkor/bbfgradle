// Original bug: KT-17390

class Test {
    private inline fun <reified T> foo() = object {
        fun check(arg: Any?) = arg is T
    }
    
    private fun bar() = foo<String>()
    fun baz() = bar()
    
}

fun main(args: Array<String>) {
    Test().baz()
}
