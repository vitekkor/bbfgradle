// Original bug: KT-9453

import kotlin.reflect.KClass

annotation class Test(vararg val values: KClass<*>)

@Test(TestClass::class)
class TestClass {

    init {
        val test = javaClass.getAnnotation(Test::class.java)
        val values = test.values
        values.forEach {
            println(it)
        }
    }

}

fun main(vararg args: String) {
    TestClass()
}
