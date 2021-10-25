// Original bug: KT-22736

class C {
    fun f() {
        g(::f1, {})
    }

    private fun f1() {}
}

inline fun g(
    p1: () -> Unit,
    p2: () -> Unit,
    p3: () -> Unit = {}
) {}
