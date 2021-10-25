// Original bug: KT-11205

class TestClass {
    inline operator fun <T> invoke(task: () -> T): T {
        return task()
    }
}

class TestCase {
    fun <T> test2(value: T): T {
        val test = TestClass()
        test {
            return value
        }

        return null as T
    }
}
