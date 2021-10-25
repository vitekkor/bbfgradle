// Original bug: KT-5448

import java.util.*

class A

class B(items: Collection<A>)

class C {
    fun foo(p: Int) {
        when (p) {
            1 -> ArrayList<Int>().add(1)
        }
    }

    fun bar() = B(listOf<A>().map { it })
}

fun main(args: Array<String>) {
    C()
}
