// Original bug: KT-20914

import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class Anno(val value: KClass<*>)

inline fun <reified T> foo() {
    object {
        init {
            println(this::foo.returnType)
        }

        fun foo(): @Anno(T::class) String = ""
    }
}

fun main(args: Array<String>) {
    foo<String>()
}
