// Original bug: KT-29712

class Vec<X: Group<X>> constructor(val vector: ArrayList<X>): List<X> by vector {
  constructor(vector: Collection<X>): this(ArrayList<X>(vector.size).apply { addAll(vector) })
  constructor(vararg vector: X): this(arrayListOf(*vector))

  operator fun plus(addend: Vec<X>): Vec<X> =
    if (size != addend.size) throw IllegalArgumentException("$size != ${addend.size}")
    else Vec(mapIndexedTo(ArrayList(size)) { index, value -> value + value } as ArrayList<X>)
}

interface Group<X: Group<X>> {
  operator fun plus(addend: X): X

  operator fun times(multiplicand: X): X
}
