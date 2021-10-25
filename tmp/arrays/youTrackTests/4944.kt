// Original bug: KT-34999

const val DEBUG = false
inline fun inlineFun(b: () -> Unit) {
    if (DEBUG) {
        inlineFunReal(b)
    }
}

inline fun inlineFunReal(b: () -> Unit) {
    try {
        b()
    } finally {
    }
}

fun builder(c: suspend () -> Unit) {}

class Sample {
    fun test() {
        inlineFun {
            builder {
                inlineFun {
                    suspendFun()
                }
            }
        }
    }

    suspend fun suspendFun() {}
}
fun main() {
    Sample().test()
}
