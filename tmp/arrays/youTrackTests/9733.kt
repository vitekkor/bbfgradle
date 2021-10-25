// Original bug: KT-2311

// 'equals' on instances of this class is useful for smart casts, because it's final
class FinalClass { 
    fun use() {}
    fun equals(x: Int): Boolean = x > 42 // Fake
}
fun foo(x: FinalClass?, y: Any) {
    if (x == y) {
        // Both OK: x is not null, y is FinalClass
        x.hashCode()
        y.use()
    }
}
