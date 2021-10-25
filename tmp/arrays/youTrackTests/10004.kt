// Original bug: KT-10230

enum class A {
    X, Y, Z
}

fun bar(): A = A.Z

fun main(args: Array<String>) {
    val z: Int
    when(bar()) {
        A.X -> z = 1
        A.Y -> z = 2
        A.Z -> z = 3
    }
    println(z)
}
