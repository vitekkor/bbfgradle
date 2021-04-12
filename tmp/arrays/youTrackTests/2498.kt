// Original bug: KT-40293

interface Semigroup<A> {
    fun A.combine(b: A): A
}

interface ByteSemigroup : Semigroup<Byte> {
    override fun Byte.combine(b: Byte): Byte = (this + b).toByte()
    // public final class test/ByteSemigroup$DefaultImpls
    //
    // JVM:
    //      public static combine(Ltest/ByteSemigroup;BB)Ljava/lang/Byte;
    //
    // JVM_IR:
    //      public static combine(Ltest/ByteSemigroup;BB)B
}
