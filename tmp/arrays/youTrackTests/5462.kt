// Original bug: KT-19571

class TestIteratorOnNullable {
    private val mmap: Map<String, List<String>> = HashMap()
    fun test(s: String) {
        val list = mmap[s]!!
        for (x in list) {
            println(x)
        }
    }
}
