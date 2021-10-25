// Original bug: KT-40757

// foo.kt

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

//@ExperimentalTime
@OptIn(ExperimentalTime::class)
class Foo {
    fun foo(d: Duration) {
    }
}

// client.kt

fun main() {
    Foo() // no error
}
