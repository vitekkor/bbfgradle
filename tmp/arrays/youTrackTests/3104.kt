// Original bug: KT-39646

// initializationInLocalClass.kt

fun bar() {
    var x: String
    object: Any() {
        init {
            x = ""
        }
    }
    // should be OK
    x.length // UNINITIALIZED_VARIABLE
}
