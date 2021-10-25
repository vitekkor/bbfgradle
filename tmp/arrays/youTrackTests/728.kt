// Original bug: KT-41770

/**
 * Polynomial coefficients without fixation on specific context they are applied to
 * @param coefficients constant is the leftmost coefficient
 */
public inline class Polynomial<T : Any>(public val coefficients: List<T>) {
    public constructor(vararg coefficients: T) : this(coefficients.toList())
}
