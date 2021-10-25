// Original bug: KT-7282

package treesetbug

import java.util.TreeSet

data class MyClass(val f: Int)
val treeSet = TreeSet<MyClass> {a, b -> a.f.compareTo(b.f)}

fun main(args: Array<String>) {
    treeSet.forEach { e : MyClass -> // doesn't work here because e is "E" instead of MyClass
    }
}
