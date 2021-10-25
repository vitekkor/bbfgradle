// Original bug: KT-14337

fun bar(flag: Boolean): String {
    var result: String? = null
    if (result == null) {
        result = if (flag) "123" else "456"
    }
    return result // Required 'String', actual 'String?'
}
