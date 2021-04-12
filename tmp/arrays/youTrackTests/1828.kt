// Original bug: KT-43286

class D(val x: UInt?)

fun f(d: D): String {
    return d.x?.let { d.x.toString() } ?: ""
}

fun box(): String =
    if (f(D(42u)) == "42") "OK" else "Fail"
