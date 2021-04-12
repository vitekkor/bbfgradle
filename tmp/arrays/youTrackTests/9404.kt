// Original bug: KT-10786

package foo

fun main(args: Array<String>) {
    val map = hashMapOf("a" to "aa", "b" to "bb")
    val keys = map.keys
    keys -= "a"
    println(map.keys) 
}
