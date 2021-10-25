// Original bug: KT-32793

@OptIn(ExperimentalUnsignedTypes::class)
suspend fun main() {
    suspend fun process(myValue: UInt) {
        println(myValue)
    }

    val value: UInt = 0u
    process(value)
}
