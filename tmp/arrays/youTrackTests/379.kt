// Original bug: KT-37354

object StringUtil {
    @Deprecated(message = "use the kotlin one instead", replaceWith = ReplaceWith("string.isNullOrEmpty()"))
    @JvmStatic
    fun isNullOrEmpty(string: CharSequence?) = string.isNullOrEmpty()
}
