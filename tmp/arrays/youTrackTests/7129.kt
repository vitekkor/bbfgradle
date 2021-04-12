// Original bug: KT-24590

package test

import kotlin.reflect.full.declaredMembers

inline fun <reified T> foo() =
        object {
            val aClass = T::class // make it specialize
            fun <X> foo() {}
        }

private fun printTypeParameters(foo: Any) {
    for (memberFunction in foo::class.declaredMembers) {
        println("-- ${memberFunction.name}:")
        for (typeParameter in memberFunction.typeParameters) {
            println(typeParameter.name)
        }
        println("----")
    }
}

fun main(args: Array<String>) {
    val foo = foo<Int>()
    printTypeParameters(foo)
}
