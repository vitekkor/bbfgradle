// Original bug: KT-27025

inline class R(private val r: Int) {
    fun test() = pf()

    companion object {
        private fun pf() = "OK"
    }
}

fun main(args: Array<String>) {
    println(R(0).test())
}
