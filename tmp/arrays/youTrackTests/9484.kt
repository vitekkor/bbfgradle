// Original bug: KT-13133

fun main(args: Array<String>) {
    inf {
        println(javaClass.name)

        // This fails with java.lang.InternalError: Malformed class name
        // the name is: ReproKt$main$$inlined$inf$lambda$1
        println(javaClass.simpleName)
    }()
}

inline fun inf(crossinline cif: Any.() -> Unit): () -> Unit {
    return {
        object : () -> Unit {
            override fun invoke() = cif()
        }
    }()
}
