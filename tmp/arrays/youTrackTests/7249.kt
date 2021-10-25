// Original bug: KT-27429

package test

inline class TestInlineClass(val value: String)

class TestClassWithInlineClass(
        val testArg1: Int,
        val testInlineClass: TestInlineClass
)

fun main() {
    for (ctor in TestClassWithInlineClass::class.java.declaredConstructors) {
        println(ctor.parameters.map { it.name })
    }
}
