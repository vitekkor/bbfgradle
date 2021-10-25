// Original bug: KT-22180

open class MemberVisibility2() {
    protected inline fun callInline() {
        val result = makePrivate()
        return
    }

    protected fun makePrivate() {}
}
