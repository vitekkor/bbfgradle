// Original bug: KT-10099

class A<X> {
    fun level(t: X): Int = TODO()
    
    fun getElementWithMinimalLevel(c: Collection<X>): X {
        assert(c.isNotEmpty())
        return c.minBy { level(it) }!! // inference failed, because X is not subtype of Any
    }
}
