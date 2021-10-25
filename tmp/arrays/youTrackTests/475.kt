// Original bug: KT-41544

class Node(val parent: Node?)

fun superParent(a: Node?): Node? {
    check(a != null)
    var p = a!! // Here is "unnecessary non-null assertion"
    while (p.parent != null) p = a.parent!!
    return p
}
