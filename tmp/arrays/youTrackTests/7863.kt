// Original bug: KT-21652

val packageList = listOf("a", "b", "c")

object MyObject {
        val list = listOf("a", "b", "c")
    }
class ListTest {

    val classList = listOf("a", "b", "c")

    init {
        val localList = listOf("a", "b", "c")

        localList[3] // Compiler should issue warning
        classList[3] // Compiler should issue warning
        packageList[3] // Compiler should issue warning
        companionList[3] // Compiler should issue warning
        MyObject.list[3] // Compiler should issue warning
    }

    fun doSomething(list: List<*>) {
        list[3] // No warning expected
    }
    
    companion object {
        val companionList = listOf("a", "b", "c")
    }
}
