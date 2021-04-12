// Original bug: KT-645

fun foo(other: Int) {}
fun <T : Int?> foo(other : T){}
fun foo(other: Short) {}
fun <T : Short?> foo(other : T) {}

fun test1() {
    foo(11) // ok
    foo(11 as Int) // ok
}
