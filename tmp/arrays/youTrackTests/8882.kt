// Original bug: KT-17972

class Test {

    val prop: String = "prop"

    fun test() =
            inlineFun {
                inlineFun2 {
                    object {
                        val inflater = prop
                    }
                }
            }
}

inline fun <T> inlineFun(init: () -> T): T {
    return init()
}

fun <T> inlineFun2(init: () -> T): T {
    return init()
}

fun main(args: Array<String>) {
    Test().test()
}
