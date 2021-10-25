// Original bug: KT-22723

fun example3(x: Any) =
    when (x) {
        !is Double -> "!Double"
        0.0 -> "0.0" // (*)
        else -> "other"
    }

fun example3a(x: Any) =
    if (x !is Double) "!Double"
    else if (x == 0.0) "0.0"
    else "other"

// example3(-0.0) == "other"
// example3a(-0.0) == "0.0"
