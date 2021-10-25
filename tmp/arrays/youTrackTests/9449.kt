// Original bug: KT-13713

interface MyIterator<out T: Any> : kotlin.collections.Iterator<T>

interface Base<T: Any> : kotlin.collections.Iterable<T> {
    override fun iterator(): MyIterator<T>
}

interface Traversable<T: Any> : Base<Any> {
    override fun iterator(): MyIterator<T> = throw NotImplementedError()
}

interface Seq<T: Any> : Traversable<T>

class Foo : Seq<String> {
    override fun iterator(): MyIterator<String> = throw NotImplementedError()
}

fun main(args: Array<String>) {
    val f: Foo = Foo() // raises ClassFormatError
}