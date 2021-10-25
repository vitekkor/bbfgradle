// Original bug: KT-41245

/**
 * I use [demo.packageone.functionToInline] here //'cannot find declaration to go' here
 */
fun useFunction() {
    val functionToInline = "functionToInline"
}
