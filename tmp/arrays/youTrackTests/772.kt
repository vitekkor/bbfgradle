// Original bug: KT-44259

fun interface IntPredicate {
    fun accept(i: Int): Boolean // No action
}

val isEven = IntPredicate {
    it % 2 == 0 // "Go To Super Method" should be available
}
