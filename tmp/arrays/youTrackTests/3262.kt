// Original bug: KT-36045

fun <K> select(x: K, y: K): K = x

fun <K> id(x: K): K = x

fun main(f: (Int) -> Unit) {
    select({}, { "str" }) // () -> Unit
    
    select({}, select({ "str" }, { 42 })) // () -> Any
    select({}, select({}, { 42 })) // () -> Unit
    
    select({}, id({ 42 })) // Error
}
