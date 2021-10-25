// Original bug: KT-34883

class Test {
    val myList = mutableMapOf<String, Int>()

    fun doSomething() {
        myList.put("first", 1)
        myList.put("second", 2)

    }
}