// Original bug: KT-43958

fun <T : Any?> T.amINull(): String = when(this) {
    null -> "oh i am null"
    else -> "yay i am ${this!!::class}" // Unnecessary non-null assertion (!!) on a non-null receiver of type T
}
