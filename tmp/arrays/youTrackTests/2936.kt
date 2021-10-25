// Original bug: KT-40234

object Scope {
    fun foo() {} // (1)

    object Nested {
//        @Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
//        @kotlin.internal.LowPriorityInOverloadResolution

        // the behaviour should be the same as with LowPriorityInOverloadResolution annotation
        @Deprecated("err", level = DeprecationLevel.HIDDEN)
        fun foo() {} // (2)

        fun take(a: Any) {}

        fun test() {
            take(::foo) // Error "DEPRECATION_ERROR", should be ok
            ::foo // ok, to (1) 
            foo() // ok to (1) 
        }
    }
}
