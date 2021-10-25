// Original bug: KT-29227

package Immutable

import kotlin.collections.map as regularMap
import kotlin.collections.mapNotNull as regularMapNotNull

sealed class ImmutableList<T>(val data: List<T>) : List<T> by data
private class ImmutableListImpl<T>(data: List<T>) : ImmutableList<T>(data)

fun <T, R> Iterable<T>.map(f: (T) -> R): ImmutableList<R> = ImmutableListImpl(this.regularMap(f))
fun <T, R : Any> Iterable<T>.mapNotNull(f: (T) -> R?): ImmutableList<R> = ImmutableListImpl(this.regularMapNotNull(f))
