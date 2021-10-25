// Original bug: KT-26979

fun <T> lastFunParameterized(last: T) {}

fun callLastFunParameterized() {
    lastFunParameterized { "a" } // Call A.
    lastFunParameterized({ "a" }) // Call B.
}
