// Original bug: KT-35884

fun bar() = foo() // call ReplaceWith, unused `import A.Companion` inserted

@Deprecated(
    "Remove",
    ReplaceWith(
        "A.aFun(\n" + ")\n" +
                ".ext()",
        "A"
    )
)
fun foo(): Any = Any()

fun Any.ext() = 5

class A {
    companion object {
        fun aFun(): Any = 5
    }
}
