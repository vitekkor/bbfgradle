// Original bug: KT-37437

fun interface SafeDeleteFace { // No action.
    fun dummy() // No action.
}

fun acceptSdf(sdf: SafeDeleteFace) {}

fun callSdf() {
    acceptSdf {}
    acceptSdf {}
}
