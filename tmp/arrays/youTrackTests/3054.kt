// Original bug: KT-32598

sealed class Stream<out A> {
    object Empty : Stream<Nothing>()
    data class Cons<out A>(val h: () -> A, val t: () -> Stream<A>) : Stream<A>()

    companion object {

        fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
            val head by lazy { hd() }
            val tail by lazy { tl() }
            return Cons({ head }, { tail })
        }

        fun <A> empty(): Stream<A> = Empty
    }
}

fun <A, S> unfold(z: S, f: (S) -> Pair<A, S>?): Stream<A> {
    val result = f(z)
    return if (result == null) Stream.empty()
    else Stream.cons({ result.first }, { unfold(result.second, f) })
}

fun <A, B> Stream<A>.zipAll(s2: Stream<B>): Stream<Pair<A?, B?>> =
    unfold(Pair(this, s2)) {
        val first = it.first
        val second = it.second
        when {
            first is Stream.Cons && second is Stream.Cons ->
                Pair(Pair(first.h(), second.h()), Pair(first.t(), second.t()))
            first is Stream.Empty && second is Stream.Cons ->
                Pair(Pair(null, second.h()), Pair(Stream.empty(), second.t()))
            first is Stream.Cons && second is Stream.Empty ->
                Pair(Pair(first.h(), null), Pair(first.t(), Stream.empty()))
            else -> null
        }
    }
