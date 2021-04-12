// Original bug: KT-22069

import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class Anno(val x: KClass<*>)

fun foo(): @Anno(Array<String>::class) Unit {}

fun main(args: Array<String>) {
    println(::foo.returnType.annotations)
}
