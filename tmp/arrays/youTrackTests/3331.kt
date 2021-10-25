// Original bug: KT-39082

enum class SignShowingPolicy {
    ALWAYS,
    ONLY_NEGATIVE,
    NEVER
}

interface SignPolicy {
    val signShowingPolicy: SignShowingPolicy
}

sealed class Format(override val signShowingPolicy: SignShowingPolicy = SignShowingPolicy.ONLY_NEGATIVE) : SignPolicy {
    /**
     * Money format for headers,
     * fraction digits are dimmed
     */
    @Deprecated("Use Format.Header")
    object HEADER : Format()

    /**
     * Money format for headers,
     * fraction digits are dimmed
     */
    data class Header(
        override val signShowingPolicy: SignShowingPolicy = SignShowingPolicy.ONLY_NEGATIVE,
        val fractionPartScale: Float
    ) : Format()

    //... other classes

}

class Example(
    val format: Format
)

val containsExample = Example(Format.HEADER)

fun main() {
    when(val format = containsExample.format) {  // here I got an error - e: org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression at (x:y)
        is Format.HEADER -> doSomething1()
        is Format.Header -> doSomething2(format.signShowingPolicy, format.fractionPartScale)
    }
}

fun doSomething2(signShowingPolicy: SignShowingPolicy, fractionPartScale: Float) {
    TODO("Not yet implemented")
}

fun doSomething1() {
    TODO("Not yet implemented")
}

