// Original bug: KT-45845

@RequiresOptIn
annotation class Experimental

class My {
    var bar: Int
        @Experimental // error: forbidden
        get() = 42
        @Experimental // Ok
        set(arg) {}
}
