// Original bug: KT-24141

import java.util.TreeSet
data class Thing(val x:String)

fun nullableThing() : Thing? {
    return null
}

fun error(){
    val selection: Set<Thing> = listOf("1", "2").mapTo(TreeSet<Thing>()) {
        nullableThing() ?: throw RuntimeException()
    }
}
