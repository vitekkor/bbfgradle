// Original bug: KT-8673

/** 
 * For each element of this [Iterable], calls the specified function [block] with the element as its receiver.
 */
fun <T> Iterable<T>.applyEach(block: T.() -> Unit) = forEach { it.block() }
