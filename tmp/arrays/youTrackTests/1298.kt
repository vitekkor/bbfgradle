// Original bug: KT-37878

fun <T : Any?> T.amINull(): String = when(this) {
    null -> "oh i am null"
    else -> "yay i am ${this!!::class}" // [UNNECESSARY_NOT_NULL_ASSERTION] Unnecessary non-null assertion (!!) on a non-null receiver of type T
}
