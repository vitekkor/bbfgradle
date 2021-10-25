// Original bug: KT-24643

package test

class Inv<T>(var t: T)

class Z<X> {
    var x: X? = null
    operator fun getValue(t: Inv<X>, m: Any) {
        val _x = x
        if (_x != null) {
            t.t = _x
        } else {
            x = t.t
        }
    }
}

val <T> Inv<T>.x: Unit by Z()

fun main(args: Array<String>) {
    val invStr = Inv("")
    val invInt = Inv(1)

    invInt.x
    invStr.x
    val s: String = invStr.t // => CCE
    println(s)
}
