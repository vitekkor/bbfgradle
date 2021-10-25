// Original bug: KT-25874

inline fun <T> Array<T>.copyFromRange(src: Array<T>, fromIndex: Int, toIndex: Int, length: Int) =
    System.arraycopy(src, fromIndex, this, toIndex, length)
// And all the primitive/unsigned arrays
