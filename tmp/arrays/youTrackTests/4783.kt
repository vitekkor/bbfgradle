// Original bug: KT-27477

class Base {
    var prev: Base? = null
    var next: Base? = null
    var obj: Any? = null
}

fun Base.iterator(): Iterator<Base> = iterator {
    var current: Base? = this@iterator
    while (current != null) {
        yield(current)
        current = current.next
    }
}
