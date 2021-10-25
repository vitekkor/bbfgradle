// Original bug: KT-17362

open class Chicken {
    companion object Egg : Chicken() {
        fun whichCameFirst() {}
    }
    init {
        whichCameFirst()
    }
}

fun main(args: Array<String>) {
    val c = Chicken()
}
