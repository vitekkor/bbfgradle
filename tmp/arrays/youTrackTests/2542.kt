// Original bug: KT-21964

val onceUsedVal = 1
val twiceUsedVal = 2
fun onceUsedFun() = 11
fun twiceUsedFun() = 22
fun useDeclared() {
    val v1 = onceUsedVal + twiceUsedVal + onceUsedFun() + twiceUsedFun()
    val v2 = twiceUsedVal + twiceUsedFun()
}
