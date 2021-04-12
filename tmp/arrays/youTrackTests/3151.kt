// Original bug: KT-39500

tailrec fun foo() { // [NO_TAIL_CALLS_FOUND] A function is marked as tail-recursive but no tail calls are found
    try {

    } catch(e: Exception) {
        foo() // [TAIL_RECURSION_IN_TRY_IS_NOT_SUPPORTED] Tail recursion optimization inside try/catch/finally is not supported
    }
}
