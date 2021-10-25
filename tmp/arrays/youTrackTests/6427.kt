// Original bug: KT-28717

import kotlin.RuntimeException

// Structured pattern matching, after a fashion, which you can then use to build other list methods.
inline fun <T, R> List<T>.match(emptyF: () -> R, nonEmptyF: (T, List<T>) -> R): R =
        if (isEmpty()) emptyF() else nonEmptyF(this[0], subList(1, size))

fun <T> List<T>.head(): T = match(
        { throw RuntimeException("can't take head of an empty list") },
        { h, _ -> h })

fun <T> List<T>.tail(): List<T> = match(
        { throw RuntimeException("can't take tail of an empty list") },
        { _, t -> t })

// if you try declaring this tailrec, it complains!
tailrec fun <T, R> List<T>.foldlNonTail(zero: R, reducer: (R, T) -> R): R = match(
        { zero },
        { h, t -> t.foldlNonTail(reducer(zero, h), reducer) }
)

// here, we inline the above by hand, and the tailrec declaration works
tailrec fun <T, R> List<T>.foldl(zero: R, reducer: (R, T) -> R): R =
        if (isEmpty()) zero else tail().foldl(reducer(zero, head()), reducer)
