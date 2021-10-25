// Original bug: KT-31941

open class Inv<T>(val value: String)

// It's important to use star-projected type as an upper bound
fun <T : Inv<*>?> test(e: T) {
    if (e != null) {
        e.value // Error in NI, OK in OI 
    }
}
