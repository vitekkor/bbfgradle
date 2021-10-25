// Original bug: KT-21919

interface Test {
    fun methodWithImpl(parameterName: String) {
        println("test")
    }
}

fun main(args: Array<String>) {
    println(
            Class.forName("Test\$DefaultImpls").declaredMethods
                    .flatMap { it.parameters.asList() }
                    .map { it.name }
    )
}
