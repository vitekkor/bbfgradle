// Original bug: KT-7461

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class Ann(val x: Array<in KClass<*>>)

@Ann(arrayOf("1", 2, 3.0)) class MyClass

fun main(args: Array<String>) {
    MyClass::class.java.getAnnotation(Ann::class.java).x //error at runtime
}
