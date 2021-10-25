// Original bug: KT-37651

sealed class SealedClass {
    class First : SealedClass()
    class Second : SealedClass()
    class Third : SealedClass()
}

fun test(sealedClass: SealedClass) {
    when (sealedClass) {
        is SealedClass.First -> "First"
        is SealedClass.Second -> "Second"
    }
}
