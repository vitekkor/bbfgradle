// Original bug: KT-12811

interface Base {
    fun foo(body: () -> String): String
}
class Derived : Base {
    override inline fun foo(body: () -> String) = body() // Error! Explicit final required, despite of 'final' modality of 'Derived'
}
