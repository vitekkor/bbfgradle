// Original bug: KT-37805

enum class TestEnum {
    VALUE_1,
    VALUE_2
}

class TestClass(val enumValue: TestEnum?) {
}

fun main() {
    val test: TestClass? = TestClass(enumValue = null)
    when (test?.enumValue) {
        null -> println("null value") // If this line is commented, compilation is ok. Otherwise, see the error below.
        TestEnum.VALUE_1 -> print("value1")
        else -> print("other value")
    }
}
