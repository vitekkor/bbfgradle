// Original bug: KT-42647

fun <T> identity(x: T): T = x
infix fun <A, B, C> ((A) -> B).andThen(g: (B) -> C): (A) -> C = { a: A -> g(this(a)) }

interface Monoid<A> {
    operator fun plus(rh: A): A
    fun empty(): A
}

fun interface Endo<A> : Monoid<Endo<A>> {
    fun call(a: A): A

    override fun empty(): Endo<A> = Endo(::identity)
    override fun plus(rh: Endo<A>): Endo<A> = Endo(::call andThen rh::call)
}

val addFive = Endo<Int> { it + 5 }
val addSix = Endo<Int> { it + 6 }

fun <A : Monoid<A>> List<A>.combineAll(): A =
    reduce { a, b -> a + b }

fun main() {
    println(listOf<Endo<Int>>(addFive, addSix).combineAll())
}
