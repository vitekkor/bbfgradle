// Original bug: KT-16247

import kotlin.reflect.KCallable

class Foo3 {
    fun <T> installRoute(handler: T) where T : (String) -> Any?, T : KCallable<*> {}

    fun foo() {
        installRoute(::route) //Overload resolution ambiguity
    }
}
fun route(s: String): Any? = null
fun route(s: Int): Any? = null
