// Original bug: KT-22723

fun example2(x: Any, y: Any) =
        x is Int && y is Double && x < y // total order comparison

fun example2a(x: Int, y: Double) =
        x < y // widening conversion for 'x' + IEEE 754 comparison
