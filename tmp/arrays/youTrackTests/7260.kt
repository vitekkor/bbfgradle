// Original bug: KT-28054

inline class Composed(val s: String) {
    private constructor(prefix: String, id: Int) : this(prefix + id)
    companion object {
        fun p1(id: Int) = Composed("p1", id)
    }   
}

val x = Composed.p1(1)
