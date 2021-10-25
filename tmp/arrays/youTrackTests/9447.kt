// Original bug: KT-13658

package foo

object O {}

operator fun O.invoke() = this

object A {
    fun foo(): Any {
        return { O() }() 
    }
}

fun main(args: Array<String>) {
    println(A.foo())
}
