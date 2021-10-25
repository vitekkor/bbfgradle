// Original bug: KT-12833

val foo: (String) -> String? = { it + "_fooed" }
val bar: (String) -> String? = { "bar" }
val baz: (String) -> String? = { if ('a' in it) "baz" else null }

val lambdasMap = mapOf(
        "foo" to foo,
        "bar" to bar,
        "baz" to baz
)
