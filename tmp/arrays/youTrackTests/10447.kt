// Original bug: KT-5697

class A : MutableIterator<Int> {
    override fun next() = 3
    override fun hasNext() = false

// Error:(6, 7) Kotlin: Platform declaration clash: The following declarations have the same JVM signature (remove()V):    fun remove(): kotlin.Unit
    override fun remove(): Unit { // without Unit ok
        throw UnsupportedOperationException()
    }
}
