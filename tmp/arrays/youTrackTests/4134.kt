// Original bug: KT-32284

class G<B>(val s: String, val b: B)

infix fun <B> String.foo(that: B): G<B> = G(this, that)
val b: G<(String) -> Any> = "" foo { _ -> Any() } // Redundant lambda arrow
