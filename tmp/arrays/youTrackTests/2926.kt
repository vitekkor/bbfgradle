// Original bug: KT-28064

inline fun inf(crossinline cif: Any.() -> String): () -> String {
    val x = {
        object : () -> String {
            override fun invoke() = cif()
        }
    }()
    return x
}
