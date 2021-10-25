// Original bug: KT-28609

package test

class A

var last: Any? = null

operator fun A.get(x: Any): Int {
    last = x
    return 42
}

operator fun A.set(x: Any, v: Int) {
    if (x === last)
        println("Same")
    else
        println("Different")
}

fun main(args: Array<String>) {
    A()[123456] += 2
}
