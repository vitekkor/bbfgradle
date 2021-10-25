// Original bug: KT-5306

fun  <A, B : Comparable<B>, C : Comparable<C>> comparator(proj1: (A) -> (B), proj2: (A) -> (C)): Comparator<A> = object : Comparator<A> {
    override fun compare(p0: A, p1: A): Int {
        val c = proj1(p0).compareTo(proj1(p1))
        return if (c != 0) c else proj2(p0).compareTo(proj2(p1))
    }
}


fun  <A, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>> comparator(proj1: (A) -> (B), proj2: (A) -> (C), proj3: (A) -> (D)): Comparator<A> = object : Comparator<A> {
    override fun compare(p0: A, p1: A): Int {
        var c = proj1(p0).compareTo(proj1(p1))
        if (c != 0) return c
        c = proj2(p0).compareTo(proj2(p1))
        return if (c != 0) c else proj3(p0).compareTo(proj3(p1))
    }
}
