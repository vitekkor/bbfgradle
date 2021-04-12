// Original bug: KT-17643

fun foo() {
    mapOf<String, Int.() -> String>(

        "1" to fun Int.() = toString(16),

        "2" to fun Int.(): String { return toString(16) },

        Pair<String, Int.() -> String>("3") { toString(16) },

        "4".to<String, Int.() -> String> { toString(16) },

        Pair("5") { toString(16) },

        "6" to { toString(16) }
    )
}
