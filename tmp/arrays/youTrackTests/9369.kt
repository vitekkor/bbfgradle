// Original bug: KT-12707

import java.util.*

fun main(args: Array<String>) {
   val f = Bar()
   println(f.list.size) // TypeError: Cannot read property 'size' of undefined
}

open class Foo(val list: List<Int>) {
    constructor() : this(ArrayList())
}

open class Bar : Foo() {}
