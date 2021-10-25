// Original bug: KT-30927

fun case_1(z: Any?) {
    val y = z.let {
        when (it) {
            is String -> return@let it
            is Float -> ""
            else -> return@let ""
        }
    }
    // y is inferred to String
}
