// Original bug: KT-37744

fun overloadFun(p: Int) {}
fun overloadFun(p: String) {}

fun <T> ambiguityFun(fn: (T) -> Unit) {}

fun overloadContext() {
    ambiguityFun { x: String -> overloadFun(x) }
}
