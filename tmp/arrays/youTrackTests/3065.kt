// Original bug: KT-31420

interface UIntFunction {
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("apply")
    fun apply(input: UInt): UInt
}

object UIntIdentity : UIntFunction {
//  public synthetic bridge apply(I)I
//  public apply-WZ4Q5Ns(I)I
    override fun apply(input: UInt): UInt =
        input
}
