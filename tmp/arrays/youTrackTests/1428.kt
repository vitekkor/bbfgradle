// Original bug: KT-39256

import kotlin.reflect.KClass

private inline fun <reified T: BaseInterface> TransformerDef<T>.toSomethingElse() = object: Transformer<T> {
    override fun supported(): KClass<T> = T::class
}

interface BaseInterface
class Impl1 : BaseInterface

interface TransformerDef<T : BaseInterface>
class SomeTransformerDef: TransformerDef<Impl1>

interface Transformer<T: BaseInterface> {
    fun supported(): KClass<T>
}

fun main() {
    val t1 = SomeTransformerDef().toSomethingElse()
    val t2 = SomeTransformerDef().toSomethingElse()

    listOf(t1, t2)
}
