// Original bug: KT-27429

inline class TestInlineClass(val value: String)

class TestClassNoInlineClass(
    val testArg1: Int,
    val testInlineClass: String
)

class TestClassWithInlineClass(
    val testArg1: Int,
    val testInlineClass: TestInlineClass
)

fun main(args: Array<String>) {
    // prints "testArg1, testInlineClass"
    println(TestClassNoInlineClass::class.java.constructors[0].parameters.map { it.name }.joinToString())
    // prints "arg0, arg1, arg2"
    println(TestClassWithInlineClass::class.java.constructors[0].parameters.map { it.name }.joinToString())
}
