// Original bug: KT-36742

fun <A: Comparable<A>, B: Comparable<B>> List<Pair<A, B>>.sorted() : List<Pair<A, B>> =
	sortedWith(
		Comparator<Pair<A, B>>{ a, b-> a.first.compareTo(b.first)}.thenBy{it.second}
	)
