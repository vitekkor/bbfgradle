// Original bug: KT-22878

fun suspend(body: () -> Int) {}

fun main(args: Array<String>) {
    val wInvokeCall = suspend { 42 } // deprecation warning on `suspend`: MODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND
}
