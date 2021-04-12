// Original bug: KT-13312

fun test1(f: (Int) -> Int) {
    println("result1 = " + f(1))
}

fun test2(f: Int.() -> Int) {
    println("result2 = " + 2.f())
}

fun main(args: Array<String>) {
    val a: (Int) -> Int = { it }
    val b: Int.() -> Int = { this }
    
    test1(a)
    test2(b)
    test1(b)
    test2(a)
}
