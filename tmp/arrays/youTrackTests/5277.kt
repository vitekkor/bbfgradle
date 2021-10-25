// Original bug: KT-9962

inline fun <T:Comparable<T>> T.compareTo(other:T, f: () -> Int): Int  {
    val cmpres=this.compareTo(other)
    if (cmpres!=0) return cmpres
    return f()
}
