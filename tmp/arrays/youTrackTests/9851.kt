// Original bug: KT-10254

//Kotlin
interface IExample {
    fun doSomethingWithList(list: List<Any>): List<Any>
    fun doSomethingWithList1(list: List<Any>): List<out Any> //redundant projection
}
