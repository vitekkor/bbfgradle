// Original bug: KT-32113

fun String?.test(): Int? =  when {
    this.isNullOrBlank() -> null
    else -> length
}

fun String?.test2(): Int? =  when {
    isNullOrBlank() -> null
    else -> length
}
