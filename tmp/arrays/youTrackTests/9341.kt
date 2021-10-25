// Original bug: KT-14442

class A {
    fun specialPrint(a: Any?) = print(a)
}

// long version, passing the outer class is required
fun List<*>.show(outer: A) {
    with(outer) {
        forEach { outer.specialPrint(it) }
    }
}
