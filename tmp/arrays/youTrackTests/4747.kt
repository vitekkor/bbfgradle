// Original bug: KT-32196

fun expanded(startIndex: Int, newCapacity: Int, buffer: Array<Any?>) {
    if (startIndex == 0) buffer.copyOf(newCapacity) else toArray(arrayOfNulls(newCapacity))
}

fun <T> toArray(array: Array<T>): Array<T> = TODO()
