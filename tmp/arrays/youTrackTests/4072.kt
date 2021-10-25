// Original bug: KT-29712

abstract class Test<X> : List<X> {
    fun <Y> foo(vector: ArrayList<Y>) {}

    fun test() {
        foo(mapIndexedTo(ArrayList(size)) { index, value -> value }) // OK in NI, error in OI
    }
}
