// Original bug: KT-30700

interface MyIterator<T> : Iterator<T>, Iterable<T>

fun foo(iterableIterator: MyIterator<Any>) {
    var i = 0;
    for (n in iterableIterator) {
        i++
    }
}
