// Original bug: KT-26568

import kotlin.reflect.KClass

annotation class Ann(val x: KClass<*>)

@Ann(Array<in String>::class)
fun foo1() {}

@Ann(Array<Array<out String>>::class)
fun foo2() {}
