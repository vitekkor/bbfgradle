// Original bug: KT-22487

interface ReturnFace {
    fun <T> provide(): T
}

fun makeError(): String { throw Exception() }

fun fail(flag: Boolean, face: ReturnFace): String {
    try {
        return makeError()
    } catch (e: Exception) {
        if (flag) {
            face.provide()
        } else throw e
    }
} // No error.
