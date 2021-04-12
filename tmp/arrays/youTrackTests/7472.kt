// Original bug: KT-25671

@Deprecated("", ReplaceWith("Bar"))
enum class Foo { INT, STRING }

fun foo(kind: Foo) { // Suggested for parameter
    println(Foo.INT) // Not suggested for expression
}
