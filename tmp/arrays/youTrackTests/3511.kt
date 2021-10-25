// Original bug: KT-37541

fun interface IsolatedFunFace {
    fun single()
}

fun referIsolatedFunFace(iff: IsolatedFunFace) {}

fun callIsolatedFunFace() {
    referIsolatedFunFace(IsolatedFunFace {})
}
