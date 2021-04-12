// Original bug: KT-24394

package some.pack

inline infix operator fun <T> Int.times(block: () -> T) =
	if (this < 0)
		throw IllegalArgumentException("Cannot repeat negative times, use 0 or greater")
	else
		(1..this).map { block() }
