// Original bug: KT-37748

fun overloadFun(p: Int) {}
fun overloadFun(p: String) {}

fun <T> ambiguityFun(fn: (T) -> Unit) {}

fun overloadContext() {
    ambiguityFun(fun(x: String) {
        overloadFun(x)
    })
}
