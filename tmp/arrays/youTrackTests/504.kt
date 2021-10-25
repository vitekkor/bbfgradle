// Original bug: KT-41771

inline class Polynomial<T : Any>(val coefficients: List<T>) {
    constructor(vararg coefficients: T) : this(coefficients.toList())
}
fun main() {
    Polynomial(arrayListOf())
}
