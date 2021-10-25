// Original bug: KT-31227

import kotlin.reflect.KClass

fun <T> arrayOfArrays(): Array<Array<T?>?> = Array(1) { null }
inline fun <reified T : Any> kcl(): KClass<T> = T::class
fun <T : Any> kClass(): KClass<Array<T>> = kcl<Array<T>>()

fun main() {
    val a: Array<Array<String?>?> = arrayOfArrays<String>() // CHECKCAST to String[][] that can't succeed
    val kClass: KClass<Array<String>> = kClass<String>() // It's expected to be String[].class there but it's effectively impossible to achieve
}
