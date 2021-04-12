// Original bug: KT-29825

class Foo {
    val normalizedPath = initialize()

    private lateinit var calculatedOffsets: IntArray

    private fun initialize() {
        calculatedOffsets = intArrayOf(1, 2, 3)
        // Next line works as expected on JVM & Native
        println("Offsets: ${calculatedOffsets.contentToString()}")
    }

    init {
        // Next line throws UninitializedPropertyAccessException on Native
        // If we put calculatedOffsets before normalizedPath it will work
        println("Offsets: ${calculatedOffsets.contentToString()}")
    }
}
