// Original bug: KT-38259

import kotlin.reflect.*

interface DelegateProvider<out T> {
	operator fun provideDelegate(receiver: Any?, prop: KProperty<*>): Lazy<T>
}

fun <Value : Any> delegate(): DelegateProvider<Value> = TODO()

fun main() {
	val value: String by delegate()  // Not enough information to infer type variable Value
}
