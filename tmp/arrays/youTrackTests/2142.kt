// Original bug: KT-37439

fun interface SafeDeleteFace {
    fun dummy() // "Safe Delete" here.
}

fun acceptSdf(sdf: SafeDeleteFace) {}

fun callSdf() {
    acceptSdf {}
    acceptSdf {}
}
