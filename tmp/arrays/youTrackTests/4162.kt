// Original bug: KT-12833

val lambdasMap: Map<String, (String) -> String?> = mapOf(
        "foo" to {it + "_fooed"},
        "bar" to {"bar"},
        "baz" to {if ('a' in it) "baz" else null}
)
