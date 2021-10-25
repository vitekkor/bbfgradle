// Original bug: KT-41817

fun test(): Int?/*caret*/ {
    fun f(): Int? {
        return null
    }
    return 1
}

fun test(list: List<Int>): Int?/*caret*/ {
    val x = list.mapNotNull {
        return@mapNotNull null
    }
    return 1
}
