// Original bug: KT-39137

open class Base<T> {

    override fun hashCode(): Int = when (this) {
        is Sub<*, *> -> wrapped.hashCode()
        else -> -1 // I expect ^^^^^^^^^^^ to be Object#hashCode, but
        // it resolves to `inline fun Any?.hashCode(): Int` call instead.
        // Proven by italic font, Ctrl+clicking, and presence of `ifnull` jump in Show Kotlin Bytecode panel
    }
    // With the override above, all wrapped.hashCode() calls resolve to Any?.hashCode();
    // If you remove it, all other wrapped.hashCode() calls will resolve to Object#hashCode()!

    fun whatever(): Int = when (this) {
        is Sub<*, *> -> wrapped.hashCode()
        else -> -1 //          ^^^^^^^^^^^ look at me when you remove `override fun hashCode`
    }
}

class Sub<T : Any, DT : Base<T>>( // not reproduces with DT : Base<Any> or Base<*>
    val wrapped: DT
) : Base<Any>()

fun Any?.whatever() = when (this) {
    is Sub<*, *> -> wrapped.hashCode()
    else -> -1 //          ^^^^^^^^^^^ look at me when you remove `override fun hashCode`
}
