// Original bug: KT-28092

inline fun foo(crossinline cb: () -> Unit) {
    val lambda = object : Function1<Unit, Unit> {
        override fun invoke(p1: Unit) {
            cb()
        }
    }
    lambda(Unit)
}
