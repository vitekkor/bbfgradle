// Original bug: KT-23863

interface Items : Countable1
interface ItemsContainer {
    val children: Items
}

interface Countable1 {}
interface Countable2 {}

fun Countable1.count(): Int = 0
fun Countable2.count(): Int = 1
