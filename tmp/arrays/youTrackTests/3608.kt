// Original bug: KT-28834

@Deprecated(message = "We don't want a typealias here", replaceWith = ReplaceWith("(Int) -> Boolean"))
typealias IntPredicate = (Int) -> Boolean

fun foo(p: IntPredicate) = p(42) // no quick fix 
val p: IntPredicate = { it > 0 } // no quick fix
