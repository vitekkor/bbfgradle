// Original bug: KT-19829

class Test {
    lateinit var mapper: TestMapper
    val nums = arrayOf(10, 20, 30, 40)

    fun doTest() {
        for (num in nums) {
            var value = mapper.get(num);
            println(value)
        }
    }
}

class TestMapper {
    fun get(num: Int) = num+10
}
