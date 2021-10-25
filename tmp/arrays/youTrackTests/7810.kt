// Original bug: KT-22495

fun testDI(x: Double, y: Int) { 
    println(x < y) 
}

fun testAA(x: Any, y: Any) { 
    println(x is Double && y is Int && x < y) 
}

fun main(args: Array<String>) {
    testDI(-0.0, 0)  // false
    testAA(-0.0, 0)  // true
}
