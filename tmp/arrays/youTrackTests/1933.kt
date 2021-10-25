// Original bug: KT-30251

class FooK {

}

fun main(args: Array<String>) {
    val classLoader = FooK::class.java.classLoader
    val resource = classLoader.getResource("test.txt")
    println(resource)
}

