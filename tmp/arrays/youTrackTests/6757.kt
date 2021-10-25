// Original bug: KT-29144

interface A {
    fun baz() {
        println(A.hashCode())
    }
    private companion object
}

class C : A

fun main(args: Array<String>) {
    C().baz()
}
