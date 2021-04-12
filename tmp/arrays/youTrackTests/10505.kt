// Original bug: KT-4288

//Example of implementation
class AnyArrayIterator<T>(private val arr: Array<T>, 
                          private val iter: Iterator<Int>): Iterator<T>, Iterable<T>{
    override fun next(): T = arr[iter.next()]
    override fun hasNext(): Boolean = iter.hasNext()
    override fun iterator() = AnyArrayIterator<T>(arr, iter)
}

fun<T> Array<T>.get(progression: Iterable<Int>): AnyArrayIterator<T>{
    return AnyArrayIterator<T>(this, progression.iterator())
}

//And the same for IntArray, ByteArray and etc
