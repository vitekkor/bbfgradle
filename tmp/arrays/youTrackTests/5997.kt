// Original bug: KT-31991

package test

import java.util.TreeSet

inline class AsInt(val value: Int)

class MyInt(val value: Int)

fun main() {
    val tree = TreeSet<MyInt>()
    tree.add(MyInt(1))
    tree.add(MyInt(2))
    tree.add(MyInt(3))
    println(tree)
}
