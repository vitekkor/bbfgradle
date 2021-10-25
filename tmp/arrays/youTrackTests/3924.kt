// Original bug: KT-35804

class TryCatchKotlin {
    @java.lang.SuppressWarnings("Something")
    fun catches() {
        try {
            canThrow()
        } catch (@java.lang.SuppressWarnings("Something") e: Throwable) { }
    }

    fun canThrow() {
    }
}
