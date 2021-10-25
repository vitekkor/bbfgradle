// Original bug: KT-43255

abstract class AC<T> : Collection<T> {
    open fun remove(x: T) = false
}

abstract class ACD : AC<Double>() {
    /*
    public final bridge remove(Ljava/lang/Object;)Z
         invokevirtual test/ACD.remove (D)Z
    public bridge remove(D)Z
         invokespecial test/ACD.remove (Ljava/lang/Object;)Z
     */
}

class CD : ACD() {
    override fun iterator(): Iterator<Double> = TODO()
    override val size: Int get() = TODO()
    override fun contains(element: Double): Boolean = TODO()
    override fun containsAll(elements: Collection<Double>): Boolean = TODO()
    override fun isEmpty(): Boolean = TODO()

    // public final bridge remove(Ljava/lang/Object;)Z
    //      invokevirtual test/CD.remove (D)Z
    // public bridge remove(D)Z
    //      invokespecial test/CD.remove (Ljava/lang/Object;)Z
}

fun main() {
    CD().remove(0.0)
}
