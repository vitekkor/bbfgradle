// Original bug: KT-39408

interface BackedByArray<BackingCollectionType> {
    val ONE: BackingCollectionType
}

@ExperimentalUnsignedTypes
object ExampleImplementation : BackedByArray<ULongArray> {
    override val ONE: ULongArray = ulongArrayOf(1u)
}

@ExperimentalUnsignedTypes
object ULongArrayReproducer {
    fun getOne() : ULongArray {
        return ExampleImplementation.ONE
    }
}

fun main() {
    ULongArrayReproducer.getOne()
}
