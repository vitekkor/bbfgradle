// Original bug: KT-15686

package plugin

annotation class NoArgClass

class NoNoArg(p: Int)
@NoArgClass class NoArg(p: Int)

fun main(args: Array<String>) {
    val nna = NoNoArg::class.java
    println(nna.constructors.size)
    val na = NoArg::class.java
    println(na.constructors.size)
}
