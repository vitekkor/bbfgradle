// Original bug: KT-6991

fun task() {
    val x : X<String> = X("") // ok, constructor with string parameter
    val y : X<String> = X<String>("") // bogus error, ambiguity
}

class X<T> {
    constructor(t:T) {}
    constructor(t:String) {}
}
