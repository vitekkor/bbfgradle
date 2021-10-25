// Original bug: KT-32256

fun main() {
    // Highlighted as red, but compiles?
    myMethod(::refA)
    myMethod(::refB)
    anotherMethod(::refA)
    anotherMethod(::refB)
}

// Note that I can't reproduce this bug when I remove the 'suspend' modifier from these methods
suspend fun refA(input: String): String {
    return input
}

suspend fun refB(): String {
    return "Hello, World!"
}

fun myMethod(f: suspend (String) -> String) {}

fun myMethod(f: suspend () -> String) {}

fun <I, O> anotherMethod(f: suspend (I) -> O) {}

fun <O> anotherMethod(f: suspend () -> O) {}
