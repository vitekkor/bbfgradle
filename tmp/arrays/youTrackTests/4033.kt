// Original bug: KT-15648

fun Any?.compareTo(other: Any?) = println("nullables")

fun<K,V> Map<K,V>.compareTo(other: Map<K,V>) = println("maps")

fun main(args: Array<String>) {
    val t: Int? = null
    t.compareTo(null) //All three OK
    null.compareTo(t)
    10.compareTo(10)

    val m: Map<Int, Int>? = null
    null.compareTo(m) //thinks that i am using function for maps
    m.compareTo(null) //thinks that i am using function for maps
}
