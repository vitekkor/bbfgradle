// Original bug: KT-31991

import java.util.TreeSet

inline class AsInt(val value: Int)

fun main() {
    val tree = TreeSet<AsInt>()
    tree.add(AsInt(1))
    tree.add(AsInt(2))
    tree.add(AsInt(3))
    println(tree)
}
