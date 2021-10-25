// Original bug: KT-13557

import java.util.*
import kotlin.properties.Delegates

fun main(args: Array<String>) {
	var foo: String by Delegates.notNull()
    bar(object : MyClass() {
        override fun baz(i: Int) {
	        foo = i.toString()
        }
    })
    println(foo)
}

fun bar(c: MyClass) {
    c.baz(3)
}

abstract class MyClass {
    abstract fun baz(i: Int)
}

