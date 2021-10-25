// Original bug: KT-14839

fun main(args: Array<String>) {
    try {
        // needs to be Java exception?
        // "t: Throwable" compiles fine
    } catch (e: java.io.IOException) {
        someFunction(e)
    }
}

// either remove inline or 2nd param to fix compile
inline fun someFunction(t: Throwable? = null,
                        bug: Boolean = true) = Unit
