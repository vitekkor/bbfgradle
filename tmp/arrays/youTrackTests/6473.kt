// Original bug: KT-30198

class C {
    @Deprecated("Binary compatibility.", level = DeprecationLevel.HIDDEN)
    fun hidden() {
        hidden()  // no recursion here, invoke extension
    }
}

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")  // <-- redundant
fun C.hidden() {
}

