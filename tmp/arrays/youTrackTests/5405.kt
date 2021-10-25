// Original bug: KT-26510

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@kotlin.internal.InlineOnly
inline fun myInlineOnly(block: () -> Unit) {
    block()
}

fun main(args: Array<String>) {
    myInlineOnly {
        println("Inside")
    }
    println("Hello")
}
