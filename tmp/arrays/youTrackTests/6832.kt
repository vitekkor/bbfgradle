// Original bug: KT-29358

@ExperimentalUnsignedTypes
fun main(args: Array<String>) {
    // Only happens for nullable types, same happens for other unsigned types
    val nullableShort: UShort? = 1.toUShort()
    if(nullableShort == UShort.MAX_VALUE) println("This won't compile")
}
