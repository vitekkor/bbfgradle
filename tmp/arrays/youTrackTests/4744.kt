// Original bug: KT-32383

interface Foo
abstract class Bar<out TFoo : Foo>

enum class AFoo : Foo
object A : Bar<AFoo>()

enum class BFoo : Foo
object B : Bar<BFoo>()

val bars = listOf(A, B)  // OI: List<Bar<*>>  NI: List<Bar<Any>>
