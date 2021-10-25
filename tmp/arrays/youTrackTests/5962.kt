// Original bug: KT-19873

open class Glass <out T> (val t: T)

interface Spill
open class Water : Spill
class SparklingWater : Water()

fun glassOfWater(w: Water): Glass<Water> = Glass(w)
fun spillGlass(g: Glass<Spill>): Spill = g.t

fun ambigous() {
    val corruptedThing = spillGlass(glassOfWater(SparklingWater()))
}
