// Original bug: KT-23791

fun <T : Any> foo /* aka foo1 */(t: T) {}
fun <T : List<Int>> foo /* aka foo2 */(t: T) {}
fun test() {
    foo<Int>(1) //wants to call foo2, which results in a compilation error, even though only foo1 applies
}
