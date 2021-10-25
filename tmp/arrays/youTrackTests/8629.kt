// Original bug: KT-20003

package test

import java.io.*

data class DC(val x: Int) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 2609897148332599114L
        val svuid = serialVersionUID
    }
}

fun main(args: Array<String>) {
    println(ObjectStreamClass.lookup(DC::class.java).serialVersionUID)
    println(DC.svuid)
}
