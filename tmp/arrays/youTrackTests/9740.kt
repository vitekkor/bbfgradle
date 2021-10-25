// Original bug: KT-11279

import kotlin.reflect.KClass

annotation class KAnn(val value: KClass<*>)
annotation class KArrayAnn(val value: Array<KClass<*>>)

@KArrayAnn(arrayOf(Test::class, Test::class))
class Test

fun test0(x: KAnn) =
        x.value.java

fun test1(x: KArrayAnn) =
        x.value[0].java

fun test2(kArrayAnn: KArrayAnn): List<Class<*>> {
    val kclasses = kArrayAnn.value
    val jclasses = kclasses.map { it.java }
    return jclasses
}
