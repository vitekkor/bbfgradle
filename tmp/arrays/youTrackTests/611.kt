// Original bug: KT-41238

typealias ResponseCallback<T> = (T) -> Unit
typealias ResponseWrapperCallback<T> = (Wrapper<T>) -> Unit

class Wrapper<T>(val data: T)

object Testing {
    fun callbackTest(callback: ResponseCallback<List<Int>>) {
        var intList: List<Int> = listOf(1,2,3,4)
        callback(intList)
    }

    fun callbackWrapperTest(callback: ResponseWrapperCallback<List<Int>>) {
        var intList: List<Int> = listOf(1,2,3,4)
        callback(Wrapper(intList))
    }
}
