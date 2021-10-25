// Original bug: KT-35485

fun main() {
    // expected 2; actual: call itself
    println(listOf("", "123", "123", "546").count("123"))
}

// so I tried call Iterable<T>.count(predicate: (T) -> Boolean): Int, but it will be compiled call itself instead of that
fun <T> Iterable<T>.count(target: T): Int {
    // there: expected kotlin.collection.count(reciever: Iterable<T>, predicator: (T) -> Boolean), actual: recursive call
    // why even spec-ed call lambda version?
    // if you run on JVM, you'll get StackOverflowError
    return this.count(object : (T) -> Boolean {
        override fun invoke(p1: T): Boolean {
            return p1 == target
        }
    })
}
