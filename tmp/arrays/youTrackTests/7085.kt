// Original bug: KT-26710

fun main(args: Array<String>) {
    repeat(10) {
        print(List(5) { it }) // WARNING: Implicit parameter 'it' of enclosing lambda is shadowed
    }
}
