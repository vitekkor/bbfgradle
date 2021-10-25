// Original bug: KT-27429

package test
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaConstructor

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
    println(TestClassNoInlineClass::class.primaryConstructor!!.parameters.map { it.name }.joinToString())
    // prints "testArg1, testInlineClass"
    println(TestClassWithInlineClass::class.primaryConstructor!!.parameters.map { it.name }.joinToString())
    // prints "testArg1, testInlineClass"
    println(TestClassNoInlineClass::class.primaryConstructor!!.javaConstructor!!.parameters.map { it.name }.joinToString())
    // prints "arg0, arg1, arg2"
    println(TestClassWithInlineClass::class.primaryConstructor!!.javaConstructor!!.parameters.map { it.name }.joinToString())
}
