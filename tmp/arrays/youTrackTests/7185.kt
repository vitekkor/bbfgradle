// Original bug: KT-25303

fun useReturnImplies0(operated: Any?) {
    checkNotNull(operated)
    println(operated.hashCode()) // Error, no smart cast.
    checkNotNull(operated, {})
    println(operated.hashCode()) // No error, smart cast.
}
