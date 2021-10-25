// Original bug: KT-36840

enum class A { V1, V2, V3 }

fun test1(a: A) {
    val x: Int
    when (a) {
        A.V1 -> x = 11
        A.V2 -> x = 22
        A.V3 -> x = 33
    }
}

fun test2(a: A) {
    when (a) {
        A.V1 -> {}
        A.V2 -> {}
        A.V3 -> {}
    }
}
