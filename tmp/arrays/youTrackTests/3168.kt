// Original bug: KT-36013

// !LANGUAGE: +NewInference +FunctionalInterfaceConversion +SamConversionPerArgument +SamConversionForKotlinFunctions
fun interface KRunnable {
    fun run()
}

fun run1(r: KRunnable) {}

fun test7(a: (Int) -> Int) {
    a as () -> Unit
    run1(a) // (*)
}
