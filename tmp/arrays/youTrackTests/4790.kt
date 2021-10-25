// Original bug: KT-8700

enum class A {
    X, Y, Z
}

fun bar(): A = A.Z

fun main(args: Array<String>) {
    val z: Int
    val b = bar()
    when (b) {
        A.X -> z = 1
        else -> error("blabla") // Nothing
    }
    println(z)
}
