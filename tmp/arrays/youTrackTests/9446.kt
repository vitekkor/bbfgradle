// Original bug: KT-13658

package foo

object O {}

operator fun O.invoke() {}

var A  = object  {
    fun foo() {
        O()
    }
}

fun main(args: Array<String>) {
    println(A)
}
