// Original bug: KT-42746

fun <K> takeLambda(x: K, fn: () -> K) {}

fun main() {
    takeLambda(10) { 0.1 } // the return type of lambda's descriptor should be Number (what K is inferred to), not Double
}
