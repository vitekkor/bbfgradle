// Original bug: KT-37488


fun markPsi(element: CustomClass) {}
fun markString(element: String) {}

sealed class SealedClass {
    object Object1 : SealedClass()

    object Object2 : SealedClass()
}

interface CustomClass

val xNull: CustomClass? = TODO()
val xNotNull: CustomClass = TODO()

