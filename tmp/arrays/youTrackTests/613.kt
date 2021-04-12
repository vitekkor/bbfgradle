// Original bug: KT-41238

typealias ResponseCallback<T> = (T) -> Unit
typealias ResponseWrapperCallback<T> = (Wrapper<T>) -> Unit

class Wrapper<T>(val data: T)

object Testing {
    fun callbackList(callback: ResponseCallback<List<Int>>) {
        var intList: List<Int> = listOf(1,2,3,4)
        callback(intList)
    }
    fun callbackArray(callback: ResponseCallback<Array<Int>>) {
        var intList: Array<Int> = arrayOf(1,2,3,4)
        callback(intList)
    }
    fun callbackWrapperArray(callback: ResponseWrapperCallback<Array<Int>>) {
        var intList: Array<Int> = arrayOf(1,2,3,4)
        callback(Wrapper(intList))
    }
    fun callbackWrapperList(callback: ResponseWrapperCallback<List<Int>>) {
        var intList: List<Int> = listOf(1,2,3,4)
        callback(Wrapper(intList))
    }
}
