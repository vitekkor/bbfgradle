// Original bug: KT-32087

fun <A> alpha() : List<A> {
    return listOf()
}

fun <B> beta(gamma: () -> List<B>) : List<B> {
    return gamma()
}

fun omega() : List<Int> {
    return beta { alpha<Int>() }
}
