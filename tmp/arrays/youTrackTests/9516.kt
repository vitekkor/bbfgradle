// Original bug: KT-10968

fun <T> getT(): T = null!!

fun getString() = ""

fun test() {
    val a : () -> String = ::getString // ok
    val b : () -> String = ::getT // strange error: required: () -> String, found KFunction0<() -> String>
}
