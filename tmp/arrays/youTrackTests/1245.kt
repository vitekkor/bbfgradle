// Original bug: KT-32711

interface I1<T>
interface I2<T>

class A {
    private val testPropertyInt = createValue<Int>()
    private inline fun <reified T> createValue() = object : I1<T>, I2<T> { }
}
