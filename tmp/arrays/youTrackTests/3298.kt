// Original bug: KT-36359

operator fun <T : Comparable<T> > ClosedRange<T>.component1() = this.start
operator fun <T : Comparable<T> > ClosedRange<T>.component2() = this.endInclusive
