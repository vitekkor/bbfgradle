// Original bug: KT-5347

fun test(x: String): Any {
    class Local {
        val xx = x
        fun clone(): Local = Local()
    }
    return Local().clone()
}

fun main(args: Array<String>) {
    println(test("").toString())
}
