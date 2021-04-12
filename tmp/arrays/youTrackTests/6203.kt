// Original bug: KT-30927

fun case_1(z: Any?) {
    val y = z.run {
        when (this) {
            is String -> this
            is Float -> ""
            else -> return@run ""
        }
    }
    // y is inferred to String
}
