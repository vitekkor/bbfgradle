// Original bug: KT-29176

import kotlin.reflect.KClass

annotation class Ann(val x: KClass<*>)

@Ann(Array<String?>::class)
fun foo1() {}

@Ann(Array<Array<Number?>?>::class)
fun foo2() {}

@Ann(Array<Array<Array<Thread?>>>::class)
fun foo3() {}
