// Original bug: KT-21886

class TestObject<T>(val value: T)

fun test(): TestObject<String> {
    return inlineTest {
        val bar = 1
        val foo: String? = null
        if (bar != 1 || foo == null)
            throw RuntimeException("")
        foo
    }
}

inline fun <T> inlineTest(body: () -> T): TestObject<T> {
    return TestObject(body())
}
