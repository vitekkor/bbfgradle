// Original bug: KT-37038

fun main() {
    val x1: String.() -> String = if (true) { -> this } else { -> this } // OK in OI, error in NI ( found `() â Unit` instead of expected `String.() -> String` )

    val x2: String.() -> String = if (true) { { this } } else { { this } } // OK in noth

    val x3: String.() -> String = { -> this } // OK in both
}
