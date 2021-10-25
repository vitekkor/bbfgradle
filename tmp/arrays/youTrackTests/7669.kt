// Original bug: KT-25874

inline fun <T> Array<T>.copyTo(dst: Array<T>, fromIndex: Int, toIndex: Int, length: Int) = 
    System.arraycopy(this, fromIndex, dst, toIndex, length)
// And all the primitive/unsigned arrays
