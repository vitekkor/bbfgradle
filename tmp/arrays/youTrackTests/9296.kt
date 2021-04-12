// Original bug: KT-14453

val <T : Any> KClass1<T>.primaryConstructor: KFunction1<T>?
    get() = null!!

interface KClass1<T : Any>
interface KFunction1<out R>
fun f(type: KClass1<*>): KFunction1<Any>? = type.primaryConstructor
