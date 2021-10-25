// Original bug: KT-40423

package a

@Deprecated(
    message = "Deprecated",
    replaceWith = ReplaceWith(
        expression = "a(a = a)",
        imports = ["b"]
    )
)
fun a(a: String) {}
