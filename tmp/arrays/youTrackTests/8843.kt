// Original bug: KT-17505

import java.util.*

class SmartSet<T> private constructor() : AbstractSet<T>() {
    companion object {
        private val ARRAY_THRESHOLD = 5

        @JvmStatic
        fun <T> create() = SmartSet<T>()

        @JvmStatic
        fun <T> create(set: Collection<T>) = SmartSet<T>().apply { this.addAll(set) }
    }

    // null if size = 0, object if size = 1, array of objects if size < threshold, linked hash set otherwise
    private var data: Any? = null

    override var size: Int = 0



    override fun iterator(): MutableIterator<T> = when {
        size == 0 -> Collections.emptySet<T>().iterator()
        size == 1 -> SingletonIterator(data as T)
        size < ARRAY_THRESHOLD -> ArrayIterator(data as Array<T>)
        else -> (data as MutableSet<T>).iterator()
    }

    override fun add(element: T): Boolean {
        when {
            size == 0 -> {
                data = element
            }
            size == 1 -> {
                if (data == element) return false
                data = arrayOf(data, element)
            }
            size < ARRAY_THRESHOLD -> {
                val arr = data as Array<T>
                if (element in arr) return false
                data = if (size == ARRAY_THRESHOLD - 1) linkedSetOf(*arr).apply { add(element) }
                else Arrays.copyOf(arr, size + 1).apply { set(size - 1, element) }
            }
            else -> {
                val set = data as MutableSet<T>
                if (!set.add(element)) return false
            }
        }

        size++
        return true
    }

    override fun clear() {
        data = null
        size = 0
    }
    
    override fun contains(element: T): Boolean = when {
        size == 0 -> false
        size == 1 -> data == element
        size < ARRAY_THRESHOLD -> element in data as Array<T>
        else -> element in data as Set<T>
    }

    private class SingletonIterator<out T>(private val element: T) : MutableIterator<T> {
        private var hasNext = true

        override fun next(): T =
                if (hasNext) {
                    hasNext = false
                    element
                }
                else throw NoSuchElementException()

        override fun hasNext() = hasNext

        override fun remove() = throw UnsupportedOperationException()
    }

    private class ArrayIterator<out T>(array: Array<T>) : MutableIterator<T> {
        private val arrayIterator = array.iterator()

        override fun hasNext(): Boolean = arrayIterator.hasNext()
        override fun next(): T = arrayIterator.next()
        override fun remove() = throw UnsupportedOperationException()
    }
}
