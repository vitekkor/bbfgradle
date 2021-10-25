// Original bug: KT-41750

fun main(args: Array<String>) {
    val c = ShowCompilerFailure()
    c.showWorkingExample()
    c.showFailure()
}

class ShowCompilerFailure {
    fun showWorkingExample() {
        val array = FailDataArray(ShortArray(5))
        val indexedArray = array.withIndex()
        for ((idx, value) in indexedArray) {
            println("$idx: $value")
        }
    }

    fun showFailure() {
        val array = FailDataArray(ShortArray(5))
        for ((idx, value) in array.withIndex()) {
            println("$idx: $value")
        }
    }
}

inline class FailData(val data: Short)


class FailDataArray(private val storage: ShortArray) : Collection<FailData> {
    operator fun get(index: Int): FailData = FailData(storage[index])
    operator fun set(index: Int, value: FailData): Unit {
        storage[index] = value.data
    }

    override val size: Int
        get() = storage.size

    override fun contains(element: FailData): Boolean {
        return storage.contains(element.data)
    }

    override fun containsAll(elements: Collection<FailData>): Boolean {
        return (elements as Collection<*>).all { it is FailData && contains(it) }
    }

    override fun isEmpty(): Boolean {
        return storage.isEmpty()
    }

    override fun iterator(): Iterator<FailData> {
        return ArrayFailDataIterator(this)
    }

}

abstract class FailDataIterator : Iterator<FailData> {
    final override fun next() = nextFailData()

    abstract fun nextFailData(): FailData
}

private class ArrayFailDataIterator(private val array: FailDataArray) : FailDataIterator() {
    private var index = 0
    override fun hasNext() = index < array.size
    override fun nextFailData(): FailData = try {
        array[index++]
    } catch (e: ArrayIndexOutOfBoundsException) {
        index -= 1; throw NoSuchElementException(e.message)
    }

}
