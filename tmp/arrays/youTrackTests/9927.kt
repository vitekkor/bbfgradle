// Original bug: KT-7944

class TestClass<T>

@JvmName("test")
fun <T: Any> test(self: TestClass<T>, other: TestClass<T>) = true
@JvmName("nullableTest")
fun <T: Any> test(self: TestClass<T?>, other: TestClass<T?>) = false

val result = test(TestClass<String>(), TestClass<String>()) 
