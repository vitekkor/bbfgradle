// Original bug: KT-45224

interface A {
    fun foo(): MutableList<String>
}

@ExperimentalStdlibApi
fun main() {
    var a: A? = null
    buildList {
        add(3)
        a = object : A {
            override fun foo(): MutableList<String> = this@buildList
        }
    }
    val x: String? = a?.foo()?.get(0) // CCE: Integer cannot be cast to String
}
