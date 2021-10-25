// Original bug: KT-11075

object TestCallableReferences {
    fun <T> foo(x: T) = x
    fun <T> foo(x: List<T>) = x

    fun test0(): (String) -> String =
            ::foo // NONE_APPLICABLE
    
    fun <T> test1(): (List<T>) -> List<T> =
            ::foo // NONE_APPLICABLE    
}
