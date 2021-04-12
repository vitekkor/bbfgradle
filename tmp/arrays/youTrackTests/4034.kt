// Original bug: KT-16249

import kotlin.reflect.*

class Foo {

    @JvmName("installRoute2")
    fun <R> installRoute(handler: KFunction1<R, Any?>) {
    }

    fun installRoute(handler: KFunction1<String, Any?>) {}

    fun foo() {
        installRoute<Int>(::route) // Error: No type arguments expected for fun installRoute(handler: KFunction1<String, Any?>): Unit
    }

}

fun route(s: String): Any? = null
fun route(s: Int): Any? = null
