// Original bug: KT-31429

package com.test

class Example {

    fun pathParam(key: String): String = "param=$key"
    inline fun <reified T : Any> pathParam(key: String) = Holder(T::class.java.getDeclaredConstructor().newInstance())

    data class Holder<T>(val value: T)
}
