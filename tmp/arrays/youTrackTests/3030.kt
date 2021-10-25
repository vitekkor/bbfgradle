// Original bug: KT-32486

interface A

class B: A {
    fun foo(){}
}

fun main() {
   val l: List<A> = listOf(B(), B())
   l.map { it as A }.asSequence().map { i -> 
        i as B // marked as not needed but required in order to compile
    }.onEach { it.foo() }
}
