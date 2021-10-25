// Original bug: KT-45074

sealed class SealedClass

class Impl : SealedClass()
class ImplTwo : SealedClass()

fun check(c: SealedClass): Unit = when (c) {
    is Impl -> TODO()
    is ImplTwo -> TODO()
}
