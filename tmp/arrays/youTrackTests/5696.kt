// Original bug: KT-28927

// commonMain

import kotlin.reflect.KClass

annotation class Anno(vararg val arg: KClass<Any>)

@Anno(Any::class)
fun hello() = "Hello"

// commonTest (or jsTest)

fun testHello() {
    hello()
}
