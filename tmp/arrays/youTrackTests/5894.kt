// Original bug: KT-22163

interface FaceA
interface FaceB
interface Generic<K>
fun <T, R> Generic<T>.translate(tf: (T) -> R): Generic<R> = TODO()

fun apply(input: Generic<FaceA>) {
    input.translate {
        if (it is FaceB) it else null
    }
}
