// Original bug: KT-29760

import java.time.Clock

inline class InlineClass(val value: Any)
class OtherClass(val value: InlineClass?)

fun main() {
    Clock.systemUTC()?.let(::InlineClass)
        ?.also {}
        .let { OtherClass(it) }
}
