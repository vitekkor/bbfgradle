// Original bug: KT-28495

inline fun <reified T> DocumentContext.read(path: String): Unit =
    runCatching { read(path, T::class.java) }
        .fold(
            { it },
            { throw IllegalStateException("Mandatory field [$path] is not present") }
        )

class DocumentContext {
    fun <T> read(path: String, java: Class<T>) {

    }
}
