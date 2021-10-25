// Original bug: KT-18577

fun indiFun(p: Int) = p + 1

fun crFun1(paramFun: (Int) -> Int) {
    var v = 0
    v += paramFun(1) // Breakpoint A.
    v += indiFun(1) // Breakpoint B.
}

fun main(args: Array<String>) {
    println(crFun1 { it + 1 })
    val localFun : (Int) -> Int = { it + 1 }
    println(crFun1 (localFun))
} 