// Original bug: KT-27028

internal fun <T, K, L> cartesianProduct(left: Iterable<T>, right: Iterable<K>, transform: (T, K) -> L): Iterable<L> {
    return left.flatMap { someT -> right.map { someK -> transform(someT, someK) } }
}
