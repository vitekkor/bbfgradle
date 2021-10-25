// Original bug: KT-30815

infix fun <A, B, C> ((A) -> B).compose(other: (B) -> C) = { a: A -> other(this(a)) }

val composition = { i: Int -> 4.0 } compose { d: Double -> "" }
// composition is inferred as (Int) -> String so it's okay
