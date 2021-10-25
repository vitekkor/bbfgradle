// Original bug: KT-27834

class A {
    fun get(x: Int): Int { // add 'operator' modifier
        return 42
    }
}

fun main(args: Array<String>) {
    A().get(42) // Could automatically apply "Replace 'get' call with indexing operator"
}
