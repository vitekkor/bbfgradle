// Original bug: KT-34952

class List {
    var foo: List? = null
    var next: List? = null
    inline fun forEachFoo(cb: (List) -> Unit) = foo.forEach(cb)
}

inline fun List?.forEach(cb: (List) -> Unit) {
    var cur = this
    while (cur != null) {
        cb(cur)
        cur = cur.next
        //check(true) { "check passed $cur" }
    }
}

fun test(bar: List?) {
    bar.forEach { list ->
        list.forEachFoo {
            println(it)
        }
    }
}
