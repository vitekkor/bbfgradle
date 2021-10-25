// Original bug: KT-18254

enum class Gender {
    MALE, FEMALE
}

inline fun postInline(block: () -> Unit) = block()

fun post(block: () -> Unit) = block()

fun main(args: Array<String>) {
    postInline { enumValueOf<Gender>("MALE") }
    post { enumValueOf<Gender>("MALE") }
}
