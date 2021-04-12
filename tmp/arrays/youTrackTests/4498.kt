// Original bug: KT-36251

class A

fun main() {
    val foo: () -> A by lazy {
        return@lazy { A() } // NI: None of the following functions is suitable, OI: OK; also it's OK without labeled return
    }
}
