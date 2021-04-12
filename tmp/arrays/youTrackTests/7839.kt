// Original bug: KT-25276

fun useReturnImplies(operated: Any?) {
    if (operated is String) {
        println(operated.substring(0)) // Line A.
    }
    require(operated is String)
    println(operated.substring(0)) // Line B.
}
