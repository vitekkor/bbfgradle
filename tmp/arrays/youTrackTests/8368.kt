// Original bug: KT-17543

enum class C {
    ONE,
    TWO;
    companion object {
        fun values() : Array<C> = emptyArray<C>()
    }
}

fun main(args: Array<String>) {
    println(C.values().size)
}
