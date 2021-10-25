// Original bug: KT-18245


annotation class NoArg

sealed class Test {

    abstract val test: String

    @NoArg
    data class Test1(
            override val test: String
    ) : Test()

    @NoArg
    data class Test2(
            override val test: String
    ) : Test()
}

fun main(args: Array<String>) {

    val problemConstructor = Test::class.java.getDeclaredConstructor()
    problemConstructor.isAccessible = true

    val javaConstructor = Test.Test1::class.java.getConstructor()
            ?: error("Did not find constructor")
    javaConstructor.isAccessible = true

    val instance = javaConstructor.newInstance() // Error

}
