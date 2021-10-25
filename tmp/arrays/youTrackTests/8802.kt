// Original bug: KT-18713

fun onSuccess(status: String) {}
fun onFailure() {}
fun test() {
    val arg: String? = System.getProperty("")
    return when {
        arg.isNullOrBlank() -> onFailure()
        else -> onSuccess(arg)
    }
}
