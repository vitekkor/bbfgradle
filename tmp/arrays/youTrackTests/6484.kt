// Original bug: KT-15391

class Foo {
    suspend operator fun invoke() = 42
}

suspend fun main() {
    val foo = Foo()
    println("Direct : ${foo()}")
    val ref = foo::invoke
    println("Via Ref: ${ref()}")
}
