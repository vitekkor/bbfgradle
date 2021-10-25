// Original bug: KT-10249

open class O {
}
class Z : O()

class B<T: O>(val o: T) {
    fun take(): T = o
    fun with(block: (T) -> Unit) {
        block(o)
    }
}

fun B<out O>.e() {
    take() // returns O
    with { val o: O = it } // lambda's `it` is Any?
}

fun main(args: Array<String>) {
    B(O()).e()
    B(Z()).e()

    B(Z()).with { val z: Z = it }
    B(O()).with { val o: O = it }
}
