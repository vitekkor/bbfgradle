// Original bug: KT-11280

class FinalClass  // <-- 'equals' on instances of this class is useful for smart casts

fun foo(x: FinalClass?, y: Any) {
    if (x == y) {
        // x should be non-null here
    }
}
