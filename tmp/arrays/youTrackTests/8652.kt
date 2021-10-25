// Original bug: KT-19892

import java.util.*

class MySet : TreeSet<Int>() {
    override fun remove(element: Int): Boolean {
        return super.remove(element)
    }
}

fun main(args: Array<String>) {
    MySet()
}
