// Original bug: KT-5199

fun test1(nonLocal: String): String {
    val localResult = doCall<String> {
        return nonLocal
    }

    return "NON_LOCAL_FAILED"
}

public inline fun <R> doCall(block: ()-> R) : R {
    return block()
}
