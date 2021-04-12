// Original bug: KT-26465

object Test {
    fun start() {
        val works = mutableListOf<String>("Hello") // Works
        val broken = mutableListOf<String>() // TypeError: ArrayList_init is not a function
    }
}
