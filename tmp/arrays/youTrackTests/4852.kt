// Original bug: KT-16643

fun main(args: Array<String>) {
    val foo: CharSequence? = null
    val bar: CharSequence? = null
    foo?.let { Unit } ?: when (bar) {
        is String -> 1
        is StringBuilder -> 2
        else -> 4  
    }
}
