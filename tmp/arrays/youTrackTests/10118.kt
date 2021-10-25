// Original bug: KT-9527

class A : Iterable<String> {
    override fun iterator(): Iterator<String> {
        return listOf("foo").iterator()
    }
}

fun main(args: Array<String>) {
    42 in A()
}
