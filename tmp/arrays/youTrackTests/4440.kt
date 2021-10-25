// Original bug: KT-36642

fun Int.reproducer() {
    fun String.foo() {
        println("${this@reproducer}") // Inspection suggests to remove redundant curly braces around $this@reproducer
    }
   "foo".foo()
}
