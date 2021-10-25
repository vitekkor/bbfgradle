// Original bug: KT-21258

class Foo {
    private val fld: String = "O"
        get() = { field }() + "K"

    val indirectFldGetter: () -> String = { fld }

    fun simpleFldGetter(): String {
        return fld
    }
}

fun main(args: Array<String>) {
    val v = Foo()
    println("indirectFldGetter: ${v.indirectFldGetter()}")
    println("simpleFldGetter: ${v.simpleFldGetter()}")
}
