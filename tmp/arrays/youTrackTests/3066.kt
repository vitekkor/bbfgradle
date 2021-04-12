// Original bug: KT-31420

interface Named {
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("name")
    val name: String
}
class MemoryNamed(
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("name")
    override val name: String
) : Named
