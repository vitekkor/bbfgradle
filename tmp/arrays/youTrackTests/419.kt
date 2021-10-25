// Original bug: KT-41135

import kotlin.reflect.KProperty

fun main() {
    println("Works: ${TypeInference().works}")
}

class TypeInference {
    val works by providerFun<TypeInference, String>()
    val doesntWork: String by providerFun()
}

fun <T, R> T.providerFun(): DelegateProvider<T, R> = object : DelegateProvider<T, R>() {
    override fun provideDelegate(thisRef: T, property: KProperty<*>): Lazy<R> {
        TODO("Not yet implemented")
    }

}

abstract class DelegateProvider<T, R> {
    abstract operator fun provideDelegate(
        thisRef: T,
        property: KProperty<*>
    ): Lazy<R>
}
