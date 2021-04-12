// Original bug: KT-28418

class Test {
    val bla = listOf("bla").sortedByDescending { it }
}
fun main(args: Array<String>) {
    println(Class.forName("Test\$\$special\$\$inlined\$sortedByDescending\$1").declaringClass)
}
