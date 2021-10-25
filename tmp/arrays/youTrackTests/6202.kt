// Original bug: KT-30927

fun case_1(z: Any?) {
    val y = z.run {
        when (this) {
            is String -> return@run this // type mismatch in the new inference (required String, found Any?)
            is Float -> ""
            else -> return@run ""
        }
    }
    // y is inferred to Any?
}
