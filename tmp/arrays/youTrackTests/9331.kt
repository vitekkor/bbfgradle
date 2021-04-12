// Original bug: KT-11088

class TestClass {
    inline operator fun <T> invoke(task: () -> T): T {
        return task()
    }
}

class TestCase {
    fun <T> test1(value: T): T {
        val test = TestClass()
        test.invoke {
            return value // << All Ok. I returning from test1 function
        }
    }

    fun <T> test2(value: T): T {
        val test = TestClass()
        test {
            return value
        }
    } // << ERROR: A 'return' expression required in a function with a block body
}