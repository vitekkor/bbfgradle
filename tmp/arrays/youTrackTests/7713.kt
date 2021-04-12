// Original bug: KT-26061

import kotlin.system.exitProcess

fun foo() {
    val a: String
    try {
        a = "Hello"
    } catch (e: Exception) {
        exitProcess(1)
    }

    println(a)
}

// Or like this:

fun bar() {
    val a = try {
        "Hello"
    } catch (e: Exception) {
        exitProcess(1)
    }

    println(a)
}
