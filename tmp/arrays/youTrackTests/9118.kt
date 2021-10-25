// Original bug: KT-15594

fun main(args: Array<String>) {
    println(ObjectThisTest.testValue)
}

object ObjectThisTest {

    val testValue: Float
        @JvmStatic get() = this.testValue2

    val testValue2: Float
        get() = 3.0f

}
