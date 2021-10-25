// Original bug: KT-2985

class Test<U> {
    // Warning [BASE_WITH_NULLABLE_UPPER_BOUND] is annoying here because we 
    // really want to be able to return null value regardless of actual 
    // nullability of U
    fun test() : U? { 
        return null
    }
}
