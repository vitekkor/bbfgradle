// Original bug: KT-37986

package test

inline class S(val x: String)

fun useS0(fn: () -> S) {
    println(fn())
}

fun useS1(s: S, fn: (S) -> S) {
    println(fn(s))
}


inline class R(val x: Any)

fun useR0(fn: () -> R) {
    println(fn())
}

fun useR1(r: R, fn: (R) -> R) {
    println(fn(r))
}


fun fnWithDefaultR(r: R = R("O")) = R(r.x.toString() + "K")

fun fnWithDefaultS(s: S = S("O")) = S(s.x + "K")


fun main() {
    useR0(::fnWithDefaultR)             // (1) OK
    useR1(R("O"), ::fnWithDefaultR)     // (2) R(x="O")K

    useS0(::fnWithDefaultS)             // (3) S(x="OK")
    useS1(S("O"), ::fnWithDefaultS)     // (4) S(x="OK")
}
