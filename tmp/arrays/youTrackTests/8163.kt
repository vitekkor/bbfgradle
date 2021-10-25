// Original bug: KT-22963

fun main(args: Array<String>) {
    val f = {
        c: Int ->
        when (c) {
            1, 2, 3 -> println("1, 2, or 3")
        }
    }    
    f(3)
}
