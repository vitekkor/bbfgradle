// Original bug: KT-27598

import kotlin.reflect.full.primaryConstructor

inline class Port(val value: Int)

val twelve = Port(12)

data class GoodDahtah(val x: Int = 12)
data class BadDahtah(val x: Port = twelve)

fun main(args: Array<String>) {
    val g = GoodDahtah::class.primaryConstructor!!.callBy(mapOf())
    println(g.x)
    val b = BadDahtah::class.primaryConstructor!!.callBy(mapOf())
    println(b.x)
}
