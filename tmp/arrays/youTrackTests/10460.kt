// Original bug: KT-5601

class Nothing() {
    fun foo(): Nothing? = this
    fun bar(): Nothing? = this
    fun cat(): Nothing? = this
}

fun main(args: Array<String>) {
    val something: Nothing? = null
    something?.foo()!!
            .bar()!!
            .cat()
}
