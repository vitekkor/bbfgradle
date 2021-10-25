// Original bug: KT-40307

abstract class MapImpl<A, B>(private val map: Map<A, B>) : Map<A, B> {
    override fun containsKey(key: A): Boolean = 
        map.containsKey(key)
}

/*
JVM:
    // access flags 0x1
    // signature (TA;)Z
    // declaration: boolean containsKey(A)
    public containsKey(Ljava/lang/Comparable;)Z

JVM_IR:
    // access flags 0x1
    public containsKey(Ljava/lang/Object;)Z
 */
