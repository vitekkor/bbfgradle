// Original bug: KT-31420

interface Named {
    @Suppress("INAPPLICABLE_JVM_NAME")
    @get:JvmName("name")
    val name: String
}

class MemoryNamed(
    override val name: String
//  public getName()Ljava/lang/String;
//  public synthetic bridge name()Ljava/lang/String;
) : Named
