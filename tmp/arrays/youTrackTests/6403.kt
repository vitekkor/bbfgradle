// Original bug: KT-23854

class Captured {
    class Inv<V>
    
    fun <K> select(x: K, y: K): K = x
    fun <T> generic(x: Inv<T>) {}
    
    fun bar(a: Inv<out Any>, b: Inv<out Any>) {
        generic(select(a, b))
    }
}
