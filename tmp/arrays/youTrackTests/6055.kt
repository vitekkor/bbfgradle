// Original bug: KT-14389

import kotlin.random.Random

fun main() {
    println(callMethodRecursivelyWithLet("helo"))
    println(callMethodRecursively("hello"))
}

// Tail recursive ok
tailrec fun callMethodRecursively(value: String): String? {
    val resultOfAnotherFunction = callOtherFunction(value)

    return if (resultOfAnotherFunction != null) {
        callMethodRecursively(resultOfAnotherFunction)
    } else {
        value
    }
}

// Not tail recursive warning is emitted
tailrec fun callMethodRecursivelyWithLet(value: String): String? {
    return callOtherFunction(value)?.let {
        callOtherFunction(value)
    }
}

fun callOtherFunction(value: String): String? = if (Random.nextBoolean()) {
    value
} else {
    null
}

