// Original bug: KT-26357

open class Unstable {
    open val x: Int? = 42 // Unstable 'val' because it's 'open' 
}

// Non-nullable overload
fun consumeInt(x: Int) {}

// Nullable overload
fun consumeInt(x: Int?) {}

fun test(u: Unstable) {
    if (u.x != null) {
        // Resolved to non-nullable overload!
        consumeInt(u.x) // Smartcast-impossible, because 'u.x' is a property that has custom or open getter
    }
}
