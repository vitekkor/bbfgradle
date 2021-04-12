// Original bug: KT-31654

class A {
    var next: A? = null
}

class B : Iterable<A> {
    var first: A? = null
        private set

    override fun iterator(): Iterator<A> {
        return iterator {
            var element = first
            while (element != null) {
                yield(element!!)
                element = element.next
            }
        }
    }
}
