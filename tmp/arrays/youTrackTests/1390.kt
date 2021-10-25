// Original bug: KT-44202

fun main() {
    // works
    println(delegated1)
    // don't works. 'getDelegated2' throws ClassCastException
    println(delegated2)
}

val primary1 = WrappedString("string")
val delegated1 by ::primary1

val primary2 = Option.none<String>()
val delegated2 by ::primary2

// inline class with Any?
// also errors with Any
inline class Option<out T : Any> 
    (val value: Any?)
//    (val value: Any)
{
    companion object {
        @PublishedApi internal val NONE_VALUE = Any()
        fun <T : Any> none() = Option<T>(NONE_VALUE)
        fun <T : Any> some(value: T) = Option<T>(value)
    }
}

// inline class with String
inline class WrappedString (val value: String?) {
}
