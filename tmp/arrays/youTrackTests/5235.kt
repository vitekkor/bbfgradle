// Original bug: KT-33765

class ExternalClass

fun ExternalClass.ext() = "1"

fun foo(block: ExternalClass.() -> Unit) {
}

fun main() {
    foo {
        ext() // `ext` can be called only in this lambda
    }
}
