// Original bug: KT-34975

class Tree(val x: Int, val left: Tree? = null, val right: Tree? = null)

fun order(T: Tree?): Sequence<Int> = sequence {
    if (T == null) return@sequence
    yieldAll(order(T.left))
    yield(T.x)
    yieldAll(order(T.right))
}
