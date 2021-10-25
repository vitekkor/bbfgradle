// Original bug: KT-28491

package test

class A

var stored: Any? = null

operator fun A.get(vararg xs: Int): Int {
    stored = xs
    return 0
}

operator fun A.set(vararg xs: Int, v: Int) {
    if (stored === xs) 
        println("Same") 
    else 
        println("$stored !== $xs")
}

fun main(args: Array<String>) {
    A()[1, 2]++
}
