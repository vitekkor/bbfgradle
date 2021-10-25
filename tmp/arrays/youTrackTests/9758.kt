// Original bug: KT-5774

interface Base<T>
fun <T, R> Base<T>.fn1(f: (T) -> R) : R = TODO()
fun <T, R, C : Base<T>> C.fn2(f: (T) -> R) : R = TODO()
fun test(b : Base<String>) {
    val xa : Int = b.fn1 { it.length } // ok
    val xb = b.fn1 { it.length } // ok, infers Int
    val ya : Int = b.fn2 { it.length } // ok
    val yb = b.fn2 { it.length } // error, cannot infer R
}
