// Original bug: KT-35485

fun <T> Iterable<T>.count(target: T): Int {
    return this.count(object : (T) -> Boolean { // no recursive call, calls `count` below
        override fun invoke(p1: T): Boolean {
            return p1 == target
        }
    })
}

fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int = 0
