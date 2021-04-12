// Original bug: KT-27441

package test.base

open class Base {
    protected val protectedVariable = "protected"

    protected inline fun protectedFunction(crossinline block: () -> Unit) {
        Runnable { println(protectedVariable); block() }.run()
    }
}
