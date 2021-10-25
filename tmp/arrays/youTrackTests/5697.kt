// Original bug: KT-28927

import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class Anno(vararg val arg: KClass<Any>)

fun foo(): List<@Anno(Any::class) String> = listOf()

fun main(args: Array<String>) {
    println(::foo.returnType.annotations)
}
