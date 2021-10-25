// Original bug: KT-13148

fun main(args: Array<String>) {
    inf {
        println(javaClass.name)

        // This fails with java.lang.InternalError: Malformed class name
        // the name is: ReproKt$main$$inlined$inf$lambda$1
        println(javaClass.simpleName)
    }.run()
}

fun <T> f(body: () -> T): T {
    return body()
}

interface Run {
    fun run()
}

inline fun inf(crossinline cif: Any.() -> Unit): Run {
    return f {
        object : Run {
            override fun run() {
                cif()
            }
        }
    }
}
