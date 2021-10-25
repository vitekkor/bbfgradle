// Original bug: KT-17444

@JvmName("fooA")
private fun String?.foo(t: String?): String? = ""
private fun String?.foo(t: String): String = ""
fun use() {
    compareBy<String> {
        "".foo("")
        "".foo(null)
    }
}
