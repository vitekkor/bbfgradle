// Original bug: KT-42776

interface NullCoercion {
    fun <T> T?.check(): T
}

private object NullCoercionImpl : NullCoercion {

    override fun <T> T?.check(): T = this ?: throw Interrupt()

}

private class Interrupt : Exception()

inline fun <R> coerceNull(block: NullCoercion.() -> R): R? {
    return try {
        NullCoercionImpl.block()
    } catch (e: Interrupt) {
        null
    }
}
