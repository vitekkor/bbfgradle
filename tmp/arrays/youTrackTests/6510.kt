// Original bug: KT-20234

package some

fun longLongLong(
    any1: Any,
    any2: Any
): Any = 1

fun test() {
    val reverseCommand =
        longLongLong(
            3,
            some.longLongLong(
                1,
                longLongLong(
                    12,
                    "Automatic reversal of unmatched reference"
                )))
}
