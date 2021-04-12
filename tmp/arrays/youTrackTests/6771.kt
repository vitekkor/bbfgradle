// Original bug: KT-11616

    interface IntPredicate : (Int) -> Boolean

    fun doProcessing(numbers : List<Int>, operation : IntPredicate) : Int {
        return numbers.filter(operation).count()
    }

    fun test1() {
        val numbers = listOf(1,2,3,4)
        val isEven = object : IntPredicate {
            override fun invoke(n: Int): Boolean {
                return n%2 == 0
            }
        }
        val result = doProcessing(numbers, isEven)
        println("test1: $result matching numbers out of ${numbers.size}")
    }
