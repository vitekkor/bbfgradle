// Original bug: KT-41238

typealias ResponseCallback<T> = (T) -> Unit
typealias ResponseWrapperCallback<T> = (Wrapper<T>) -> Unit

class Wrapper<T>(val data: T)

object Testing {
    fun callbackTest(callback: ResponseCallback<Array<Int>>) {
        var intArray: Array<Int> = arrayOf(1,2,3,4)
        callback(intArray)
    }

    fun callbackWrapperTest(callback: ResponseWrapperCallback<Array<Int>>) {
        var intArray: Array<Int> = arrayOf(1,2,3,4)
        callback(Wrapper(intArray))
    }
}

public fun <T> Array<T>.iterateOverArray(): Iterator<out T> = this.iterator()
