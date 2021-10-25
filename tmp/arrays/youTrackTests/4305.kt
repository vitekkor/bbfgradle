// Original bug: KT-36885

val x: String? = "..."

val condition1 = true
val condition2 = false

val myVal = // inferred type is String? when String expected
    if (condition1) {
        x
    }
    else if (condition2) {
        x
    }
    else {
        x
    } ?: "return"
