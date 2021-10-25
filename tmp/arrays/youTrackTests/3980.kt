// Original bug: KT-24886

fun <T : Comparable<T>> test(a: Iterable<T>) {
    val x = if (booleanExpr1()) // Type inference for control flow expression failed. Please specify its type explicitly.
        booleanExpr2()
    else if (booleanExpr3())
        booleanExpr4()
    else {
        a.iterator().next() // Type mismatch: inferred type is T but Comparable<Boolean> was expected
    }
}

fun booleanExpr1() = true
fun booleanExpr2() = true
fun booleanExpr3() = true
fun booleanExpr4() = true
