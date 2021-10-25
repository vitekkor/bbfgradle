// Original bug: KT-40269

class Either

infix fun <A, B, C> ((A) -> B).andThen(g: (B) -> C) = null as (A) -> C

fun unsafeRunAsync(cb: (Either) -> Unit) {}

fun runAsync(cb: (Either) -> Unit) {
    unsafeRunAsync(cb.andThen { })
}
