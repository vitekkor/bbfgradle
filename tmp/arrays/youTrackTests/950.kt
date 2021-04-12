// Original bug: KT-12109

fun <T, R : Any> Iterable<T>.firstNotNull(selector: (T) -> R?): R = mapNotNull(selector).first()

fun <T, R : Any> Iterable<T>.firstNotNullOrNull(selector: (T) -> R?): R? = mapNotNull(selector).firstOrNull()
