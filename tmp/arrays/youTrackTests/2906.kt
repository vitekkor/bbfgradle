// Original bug: KT-40254

fun <T> List<Option<T>>.flatten(): List<T> = flatMap { it.fold(::emptyList, ::listOf) }

class Option<out T> {
    fun <R, A> fold(ifEmpty: () -> R, ifSome: (A) -> R): R = TODO()
}
