// Original bug: KT-19100

open class KFoo {
    fun foo(): String {
        if (this is KFooQux) return qux
        throw AssertionError()
    }
}

class KFooQux : KFoo()
val KFooQux.qux get() = "OK"

fun main(args: Array<String>) {
    println(KFooQux().foo())
}
