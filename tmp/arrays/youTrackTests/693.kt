// Original bug: KT-42733

private fun foo(): Any? {
    run {
        try {
            var definitelyUsed = ""
            return object {
                fun foo() = definitelyUsed
                fun bar() = ""
            }
        } catch (e: Throwable) {
            return null
        }
    }
}
