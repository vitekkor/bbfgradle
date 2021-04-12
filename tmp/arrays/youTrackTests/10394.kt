// Original bug: KT-1687

// A.kt
abstract class A() : B() {
}
open class B() {
    public val badVal : Int = 123
}

// T.kt
class T() : A() {
    fun aa() = println(badVal)
}
fun main(args : Array<String>) {
    T().aa()
}
