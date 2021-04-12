// Original bug: KT-37697

class Expression<T>

object Row {
    operator fun <T> get(c: Expression<T>): T {
        TODO()
    }
}

fun main() {

    val expr = Expression<Boolean>()

    val b: Boolean = Row[expr as Expression<*>] == "" // After applying intention "Remove useless cast", EQUALITY_NOT_APPLICABLE error occurs
}
