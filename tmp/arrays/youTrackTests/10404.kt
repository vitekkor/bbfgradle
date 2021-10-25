// Original bug: KT-5930

var s = ""

fun getA(a: Int) : Int {
    s += "getA()->"
    return a
}

fun getB(b : Int.(Int)->Int): Int.(Int)->Int {
    s+= "getB()->"
    return b
}

fun main(args: Array<String>) {
    getA(1).(getB({ this+it+2 }))(1)
    print(s)
}
