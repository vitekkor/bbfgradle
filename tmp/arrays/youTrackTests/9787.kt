// Original bug: KT-6982

class Klass {
    constructor(a: Int) {}
    constructor(a: String) {}
}

fun user(f: (Int) -> Klass) {}

fun fn() {
    user(::Klass) // bogus error, overload ambiguity
}
